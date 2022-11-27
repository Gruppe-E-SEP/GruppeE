package sep.tippspiel.systemadministrator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import sep.tippspiel.liga.LigaService;
import sep.tippspiel.mannschaft.Mannschaft;
import sep.tippspiel.spiel.Spiel;
import sep.tippspiel.spiel.SpielService;
import sep.tippspiel.systemdatum.SystemDatumService;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@RestController
@RequestMapping(value = "/administrator")
@CrossOrigin(origins = "http://localhost:4200")
public class SystemadministratorController {

    @Autowired
    SystemadministratorService systemadministratorService;
    @Autowired
    SystemDatumService systemDatumService;
    @Autowired
    SpielService spielService;

    @Autowired
    LigaService ligaService;

    @PostMapping(path = "/createSA",  produces = "application/json", consumes = "application/json")
    public ResponseEntity<String> createUser(@RequestBody Systemadministrator sa) {

        if(this.systemadministratorService.isValidEmailAddress(sa.getEmail())) {
            if(this.systemadministratorService.findByEmail(sa.getEmail())!=null) {
                return new ResponseEntity<>("User mit diesen E-Mail-Adresse ist bereits registriert", HttpStatus.OK);
            } else {
                if(this.systemadministratorService.createSystemadministrator(sa.getVorname(), sa.getNachname(), sa.getEmail(), sa.getPasswort())) {
                    return new ResponseEntity<>("User wurde erstellt:", HttpStatus.OK);
                } else {
                    return new ResponseEntity<>("User konnte nicht erstellt werden", HttpStatus.BAD_REQUEST);
                }
            }
        } else {
            return new ResponseEntity<>("Email ist ungültig", HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping(path = "/allSA", produces = "application/json")
    public ResponseEntity<List<Systemadministrator>> getAllSA() {
        List<Systemadministrator> allSA = this.systemadministratorService.all();
        return new ResponseEntity<>(allSA, HttpStatus.OK);
    }

    @GetMapping(path = "/findByNameSA/{vorname}", produces = "application/json")
    public ResponseEntity<List<Systemadministrator>> getSAByName(@PathVariable String vorname) {
        List<Systemadministrator> saByName = this.systemadministratorService.findByName(vorname);
        return new ResponseEntity<>(saByName, HttpStatus.OK);
    }

    @PostMapping(path = "/loadcsvf")
    public ResponseEntity<String> loadCSVF(@RequestParam("csv") MultipartFile multipartFile) {
        if(this.systemadministratorService.istCSVFormat(multipartFile)) {
            File file = new File("src/main/resources/targetFile.tmp");

            try (OutputStream os = new FileOutputStream(file)) {
                os.write(multipartFile.getBytes());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            systemadministratorService.csvEinlesen(file);
            return new ResponseEntity<>("CSV wurde importiert", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Datei konnte nicht importiert werden. Verwenden Sie bitte ausschließlich CSV Format ", HttpStatus.BAD_REQUEST);
        }
    }

/*    @PostMapping(path = "/setSystemDatum", produces = "application/json", consumes = "application/json")
    public ResponseEntity<String> setSystemDatum(@RequestParam Date date) {

        if(this.systemadministratorService.setSystemDatum(date)){
            return new ResponseEntity<String>("Datum wurde aktualisiert", HttpStatus.OK);
        } else {
            return new ResponseEntity<String>("Datum konnte nicht aktualisiert werden", HttpStatus.BAD_REQUEST);
        }
    }*/

    @PostMapping(path = "/setSystemDatum", produces = "application/json", consumes = "application/json")
    public ResponseEntity<String> setDate(@RequestParam("date")@DateTimeFormat(pattern = "yyyy-MM-dd") Date date) {
        if(systemDatumService.setDate(date)) {
            return new ResponseEntity<>("Datum wurde aktualisiert", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Datum konnte nicht aktualisiert werden", HttpStatus.BAD_REQUEST);
        }
    }

    //
    @PutMapping(path = "/setspieldate", produces = "application/json", consumes = "application/json")
    public ResponseEntity<String> setSpielDatum(@RequestParam("id") Long id, @RequestParam("date") @DateTimeFormat(pattern = "yyyy-MM-dd") Date date) {

        if(this.spielService.setSpielDate(id,date)) {
            return new ResponseEntity<>("Spieldatum wurde aktualisiert", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Datum konnte nicht aktualisiert werden",  HttpStatus.BAD_REQUEST);
        }
    }


    @PostMapping(path = "/createliga", produces = "application/json", consumes = "application/json")
    public ResponseEntity<String> createLiga(@RequestParam("name") String name) {
        if(this.ligaService.createLiga(name)) {
            return new ResponseEntity<>("Liga mit name " + name + " wurde erstelt", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Liga konnte nicht erstellt werden", HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(path = "/setspiel")
    public ResponseEntity<String> setSpiel(@RequestParam("name")String name,@RequestParam("spielplanId")long spielplanId,@RequestParam("tag") int tag,
                                           @RequestParam("date") @DateTimeFormat(pattern = "yyyy-MM-dd")Date date,@RequestParam("score") String score,@RequestParam("mannschaft1") String mannschaft1,
                                           @RequestParam("mannschaft2")String mannschaft2) throws ParseException {
        if(this.systemadministratorService.setSpiel(name,spielplanId,tag,date,score,mannschaft1,mannschaft2)) {
            return new ResponseEntity<>("Spiel wurde erfolgreich angelegt", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Spiel konnte nicht angelegt werden", HttpStatus.BAD_REQUEST);
        }
    }

/*    @PutMapping(path = "/findheimmannschaftscore", produces = "application/json")
    public ResponseEntity<List<String>> findHeimMannschaftScore(@RequestParam String name) {
        List<String> scoreList = this.spielService.findHeimMannschaftScore(name);;
        return new ResponseEntity<List<String>>(scoreList, HttpStatus.OK);
    }

    @PutMapping(path = "/findgastmannschaftscore", produces = "application/json")
    public ResponseEntity<List<String>> findGastmannschaftScore(@RequestParam String name) {
        List<String> scoreList = this.spielService.findGastmannschaftScore(name);;
        return new ResponseEntity<List<String>>(scoreList, HttpStatus.OK);
    }*/

    @PutMapping(path = "/gibtipphilfe", produces = "application/json")
    public ResponseEntity<String> gibMannschaftVorhersage(@RequestParam String name) {
        String vorhersage = this.spielService.calcVorhersage(name);
        return new ResponseEntity<String>(vorhersage, HttpStatus.OK);
    }

}
