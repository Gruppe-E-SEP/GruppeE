package sep.tippspiel.systemadministrator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sep.tippspiel.user.Users;

import java.util.List;

@RestController
@RequestMapping(value = "/administrator")
@CrossOrigin(origins = "http://localhost:4200")
public class SystemadministratorController {

    @Autowired
    SystemadministratorService systemadministratorService;

    @PostMapping(path = "/createSA",  produces = "application/json", consumes = "application/json")
    public ResponseEntity<String> createUser(@RequestBody Systemadministrator sa) {

        if(this.systemadministratorService.findByEmail(sa.getEmail())!=null) {
            return new ResponseEntity<>("User mit diesen E-Mail-Adresse ist bereits registriert", HttpStatus.OK);
        } else {
            if(this.systemadministratorService.createSystemadministrator(sa.getVorname(), sa.getNachname(), sa.getEmail(), sa.getPasswort())) {
                return new ResponseEntity<>("User wurde erstellt:", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("User konnte nicht erstellt werden", HttpStatus.BAD_REQUEST);
            }
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




}