package sep.tippspiel.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

import static sep.tippspiel.user.UserService.isValidEmailAddress;

@RestController
@RequestMapping(value = "/user")
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping(path = "/create",  produces = "application/json", consumes = "application/json")
    public ResponseEntity<String> createUser(@RequestBody Users user) {

        if(isValidEmailAddress(user.getEmail())){
            if(this.userService.findByEmail(user.getEmail())!=null) {
                return new ResponseEntity<>("User mit diesem E-Mail-Adresse ist bereits registriert", HttpStatus.OK);
            } else {
                if(this.userService.createUser(user.getVorname(), user.getNachname(), user.getEmail(), user.getPasswort())) {
                    return new ResponseEntity<>("User wurde erstellt:", HttpStatus.OK);
                } else {
                    return new ResponseEntity<>("User konnte nicht erstellt werden", HttpStatus.BAD_REQUEST);
                }
            }
        } else {
            return new ResponseEntity<>("Email ist ungültig:", HttpStatus.OK);
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


}
