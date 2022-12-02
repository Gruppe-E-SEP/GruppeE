package sep.tippspiel.user;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import sep.tippspiel.liga.LigaService;
import sep.tippspiel.spiel.Spiel;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import static sep.tippspiel.user.UserService.isValidEmailAddress;

@RestController
@RequestMapping(value = "/user")
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {

    @Autowired
    UserService userService;
    @Autowired
    LigaService ligaService;

    @Autowired
    UserRepository userRepository;


    @PostMapping(path = "/create", produces = "application/json", consumes = "application/json")
    public ResponseEntity<String> createUser(@RequestBody Users user) {
        System.out.println(user);
        if (isValidEmailAddress(user.getEmail())) {
            if (this.userService.findByEmail(user.getEmail()) != null) {
                return new ResponseEntity<>("User mit diesem E-Mail-Adresse ist bereits registriert", HttpStatus.OK);
            } else {
                if (this.userService.createUser(user.getVorname(), user.getNachname(), user.getDate(), user.getEmail(), user.getPasswort())) {
                    return new ResponseEntity<>("User wurde erstellt:", HttpStatus.OK);
                } else {
                    return new ResponseEntity<>("User konnte nicht erstellt werden", HttpStatus.BAD_REQUEST);
                }
            }
        } else {
            return new ResponseEntity<>("Email ist ungültig:", HttpStatus.BAD_REQUEST);
        }


    }

    @GetMapping(path = "/all", produces = "application/json")
    public ResponseEntity<List<Users>> getAllUsers() {
        List<Users> allUsers = this.userService.all();
        return new ResponseEntity<>(allUsers, HttpStatus.OK);
    }

    @GetMapping(path = "/findByName/{vorname}", produces = "application/json")
    public ResponseEntity<List<Users>> getUsersByName(@PathVariable String vorname) {
        List<Users> usersByName = this.userService.findByName(vorname);
        return new ResponseEntity<>(usersByName, HttpStatus.OK);
    }

    @GetMapping(path = "/allspiele", produces = "application/json")
    public ResponseEntity<List<Spiel>> getAllSpiele() {
        List<Spiel> allspiele = this.userService.allspiele();
        return new ResponseEntity<>(allspiele, HttpStatus.OK);
    }

    @PutMapping(path = "/setuserimage")
    public ResponseEntity<String> setUserImage(@RequestParam String email, @RequestParam("image") MultipartFile multipartFile) {

        if (this.ligaService.istImageFormat(multipartFile)) {
            Long id = this.userRepository.findUserIdByEmail(email);
            String filename = "src/main/resources/userImage" + id.toString() + ".jpeg";

            if (this.userService.setUserImage(email, filename)) {
                File file = new File(filename);

                try (OutputStream os = new FileOutputStream(file)) {
                    os.write(multipartFile.getBytes());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                return new ResponseEntity<>("UserImage wurde gespeichert", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Image konnte nicht gespeichert werden", HttpStatus.BAD_REQUEST);
            }

        }
        return new ResponseEntity<>("Es darf nur JPEG Format verwendet werden", HttpStatus.BAD_REQUEST);
    }

    @PostMapping(path = "/login", produces = "application/json", consumes = "application/json")
    public ResponseEntity<String> loginUser(@RequestBody User login) {
        if (this.userService.loginUser(login.getEmail(), login.getPassword())){
            Long id = this.userRepository.findUserIdByEmail(login.getEmail());

            Users user = this.userRepository.getReferenceById(id);
            System.out.println(user.isLoggedIn());
            return new ResponseEntity<>("Login erfolgreich", HttpStatus.OK);
        }

        return new ResponseEntity<>("Login fehlgeschlagen", HttpStatus.BAD_REQUEST);
    }


    @PostMapping(path = "/logout", produces = "application/json", consumes = "application/json")
    public ResponseEntity<String> logOutUser(@RequestParam String email) {
        if (this.userService.logUserOut(email)) {
            Long id = this.userRepository.findUserIdByEmail(email);

            Users user = this.userRepository.getReferenceById(id);
            System.out.println(user.isLoggedIn());
            return new ResponseEntity<>("Logout erfolgreich", HttpStatus.OK);
        }

        return new ResponseEntity<>("Logout fehlgeschlagen", HttpStatus.BAD_REQUEST);

    }
}
