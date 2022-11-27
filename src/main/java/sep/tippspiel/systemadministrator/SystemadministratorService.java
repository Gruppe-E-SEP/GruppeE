package sep.tippspiel.systemadministrator;

import com.google.common.hash.Hashing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;
import sep.tippspiel.liga.Liga;
import sep.tippspiel.liga.LigaRepository;
import sep.tippspiel.mannschaft.Mannschaft;
import sep.tippspiel.mannschaft.MannschaftRepository;
import sep.tippspiel.spiel.Spiel;
import sep.tippspiel.spiel.SpielRepository;
import sep.tippspiel.spielplan.Spielplan;
import sep.tippspiel.spielplan.SpielplanRepository;
import sep.tippspiel.spieltag.Spieltag;
import sep.tippspiel.spieltag.SpieltagRepository;
import sep.tippspiel.systemdatum.SystemDatum;
import sep.tippspiel.systemdatum.SystemDatumRepository;
import sep.tippspiel.user.Users;


import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.servlet.ServletContext;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.util.Date;
import java.util.List;



@Service
public class SystemadministratorService {

    @Autowired
    private SystemadministratorRepository systemadministratorRepository;
    @Autowired
    private MannschaftRepository mannschaftRepository;
    @Autowired
    private SpielRepository spielRepository;
    @Autowired
    private SpieltagRepository spieltagRepository;
    @Autowired
    private SpielplanRepository spielplanRepository;
    @Autowired
    private LigaRepository ligaRepository;
    @Autowired
    private SystemDatumRepository systemDatumRepository;


    public boolean createSystemadministrator(String vorname, String nachname, String email,  String passwort){

        try {
            String sha256hex = Hashing.sha256()
                    .hashString(passwort, StandardCharsets.UTF_8)
                    .toString();
            Systemadministrator Systemadministrator = new Systemadministrator(vorname, nachname, email, sha256hex);
            systemadministratorRepository.save(Systemadministrator);
            return true;
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return false;
        }

    }

    public List<Systemadministrator> all() {return systemadministratorRepository.findAll();}

    public String findByEmail(String email) {
        System.out.println(this.systemadministratorRepository.findByEmail(email)==null);
        return this.systemadministratorRepository.findByEmail(email);
    }

    public List<Systemadministrator> findByName(String vorname) {
        System.out.println(this.systemadministratorRepository.findByName(vorname));
        return this.systemadministratorRepository.findByName(vorname);
    }

    public boolean isValidEmailAddress(String email) {
        boolean result = true;
        try {
            InternetAddress emailAddr = new InternetAddress(email);
            emailAddr.validate();
        } catch (AddressException ex) {
            result = false;
        }
        return result;
    }

    public void csvEinlesen(File csv) {
        String line = "";
        final String delimiter = ",";

        try {FileReader fileReader = new FileReader(csv);


            BufferedReader reader = new BufferedReader(fileReader);

            Spielplan spielplan = new Spielplan();
            this.spielplanRepository.save(spielplan);

            Liga liga = new Liga("1. Bundesliga", this.spielplanRepository.getReferenceById(this.spielplanRepository.getMaxId()), "");

            this.ligaRepository.save(liga);
            reader.readLine();
            while ((line = reader.readLine()) !=null) {

                String[] token = line.split(delimiter);

                if(!this.mannschaftRepository.isMannschaftPresent(token[2])){
                    this.mannschaftRepository.save(new Mannschaft(token[2]));
                }

                Long id1 = this.mannschaftRepository.findByName(token[2]);

                if(!this.mannschaftRepository.isMannschaftPresent(token[4])){
                    this.mannschaftRepository.save(new Mannschaft(token[4]));
                }

                Long id2 = this.mannschaftRepository.findByName(token[4]);
                System.out.println(token[0] + " ! " + token[1] + " !" + token[2] + " ! " + token[3] + " ! " + token[4]);

                if(!this.spieltagRepository.isSpieltagPresent(Integer.parseInt(token[0]))) {
                    this.spieltagRepository.save(new Spieltag(Integer.parseInt(token[0]),this.spielplanRepository.getReferenceById((this.spielplanRepository.getMaxId()))));
                }

                Long sId = this.spieltagRepository.getByTag(Integer.parseInt(token[0]));

                this.spielRepository.save(new Spiel(this.mannschaftRepository.getReferenceById(id1), this.mannschaftRepository.getReferenceById(id2),token[1], token[3], this.spieltagRepository.getReferenceById(sId)));

            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

    }

/*    public boolean setSystemDatum(Date date) {
        try {
            SystemDatum systemDatum = new SystemDatum(date);
            this.systemDatumRepository.deleteAll();
            this.systemDatumRepository.save(systemDatum);
            return true;
        } catch (Exception e) {
            e.getMessage();
            return false;
        }
    }*/

    public boolean istCSVFormat(MultipartFile file) {
        String TYPE = "text/csv";

        if (!TYPE.equals(file.getContentType())) {
            return false;
        }
        return true;
    }

    public boolean setSpiel(String name,long spielplanId, int tag, Date date, String score, String mannschaft1,String mannschaft2 ) throws ParseException {

        Spielplan spielplan;
        if(!this.spielplanRepository.existsById(spielplanId)) {
            spielplan = new Spielplan();
            this.spielplanRepository.save(spielplan);
        } else {
            spielplan = this.spielplanRepository.getReferenceById(spielplanId);
        }

        Liga liga;
        if(!ligaRepository.existsByName(name)) {
            liga = new Liga();
            liga.setName(name);
            this.ligaRepository.save(liga);

        } else {
            liga = this.ligaRepository.getReferenceById(ligaRepository.findByName(name));
        }

        liga.setSpielplan(this.spielplanRepository.getReferenceById(spielplan.getId()));

        Mannschaft heimmannschaft = new Mannschaft();
        Long heimmannschaftId;
        if(!this.mannschaftRepository.isMannschaftPresent(mannschaft1)) {
            heimmannschaft.setName(mannschaft1);
            this.mannschaftRepository.save(heimmannschaft);
            heimmannschaftId = this.mannschaftRepository.findByName(mannschaft1);
        } else {
            heimmannschaftId = this.mannschaftRepository.findByName(mannschaft1);
        }

        Mannschaft auswaertsmannschaft = new Mannschaft();
        Long auswaertsmannschaftId;
        if(!this.mannschaftRepository.isMannschaftPresent(mannschaft2)) {
            auswaertsmannschaft.setName(mannschaft2);
            this.mannschaftRepository.save(auswaertsmannschaft);
            auswaertsmannschaftId = this.mannschaftRepository.findByName(mannschaft2);
        } else {
            auswaertsmannschaftId = this.mannschaftRepository.findByName(mannschaft2);

        }

        Spieltag spieltag = new Spieltag();
        spieltag.setSpielplan(this.spielplanRepository.getReferenceById(spielplanId));
        Long spielTagId;
        System.out.println("String Date: " + date.toString());

        if(!this.spieltagRepository.isSpieltagPresent(tag)) {
            spieltag.setTag(tag);
            this.spieltagRepository.save(spieltag);
            spielTagId = this.spieltagRepository.getByTag(tag);
        } else {
            spielTagId = this.spieltagRepository.getByTag(tag);
        }

        this.spielRepository.save(new Spiel(this.mannschaftRepository.getReferenceById(heimmannschaftId),this.mannschaftRepository.getReferenceById(auswaertsmannschaftId),date.toString(),score,this.spieltagRepository.getReferenceById(spielTagId)));

        return true;
    }

}
