package sep.tippspiel.user;

import com.google.common.hash.Hashing;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sep.tippspiel.liga.LigaService;
import sep.tippspiel.spiel.Spiel;
import sep.tippspiel.spiel.SpielRepository;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import sep.tippspiel.mailingsystem.SendMail;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private SpielRepository spielRepository;

    @Autowired
    private SendMail sendMail;



    public boolean createUser(String vorname, String nachname, Date date, String email, String passwort){

        try {
            String sha256hex = Hashing.sha256()
                    .hashString(passwort, StandardCharsets.UTF_8)
                    .toString();
            Users user = new Users(vorname, nachname, date, email, sha256hex);
            userRepository.save(user);
            return true;
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return false;
        }

    }


    public List<Users> all() {return userRepository.findAll();}

    public String findByEmail(String email) {
        return this.userRepository.findByEmail(email);
    }

    public List<Users> findByName(String vorname) {
        return this.userRepository.findByName(vorname);
    }

    public static boolean isValidEmailAddress(String email) {
        boolean result = true;
        try {
            InternetAddress emailAddr = new InternetAddress(email);
            emailAddr.validate();
        } catch (AddressException ex) {
            result = false;
        }
        return result;
    }

    public List<Spiel> allspiele() {return spielRepository.findAll();}

    public boolean setUserImage(String email, String userImgage) {
        try {
            Long id = this.userRepository.findUserIdByEmail(email);

            Users user = this.userRepository.getReferenceById(id);
            user.setImage(userImgage);
            this.userRepository.save(user);
            return true;
        } catch (Exception e) {
            System.err.println("UserImage konnte nicht gesetzt werden" + e.getStackTrace());
            return false;
        }
    }


}
