package sep.tippspiel.user;

import com.google.common.hash.Hashing;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;


    public boolean createUser(String vorname, String nachname, String email,  String passwort){

        try {
            String sha256hex = Hashing.sha256()
                    .hashString(passwort, StandardCharsets.UTF_8)
                    .toString();
            Users user = new Users(vorname, nachname, email, sha256hex);
            userRepository.save(user);
            return true;
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return false;
        }

    }

    public List<Users> all() {return userRepository.findAll();}

    public String findByEmail(String email) {
        System.out.println(this.userRepository.findByEmail(email)==null);
        return this.userRepository.findByEmail(email);
    }

    public List<Users> findByName(String vorname) {
        System.out.println(this.userRepository.findByName(vorname));
        return this.userRepository.findByName(vorname);
    }
        

}
