package sep.tippspiel.systemadministrator;

import com.google.common.hash.Hashing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sep.tippspiel.user.Users;

import java.nio.charset.StandardCharsets;
import java.util.List;
@Service
public class SystemadministratorService {

    @Autowired
    private SystemadministratorRepository systemadministratorRepository;

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

}
