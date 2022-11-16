package sep.tippspiel.liga;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/liga")
@CrossOrigin(origins = "http://localhost:4200")
public class LigaController {

    @Autowired
    LigaService ligaService;

    @PutMapping(path = "/saveliganame", produces = "application/json", consumes = "application/json")
    public ResponseEntity<String> saveLigaName(@RequestParam String oldName,@RequestParam String newName) {
        if(this.ligaService.setLigaName(oldName, newName)) {
            return new ResponseEntity<>("Liganame wurde erfolgreich geändert", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Liganame konnte nicht geändert werden", HttpStatus.BAD_REQUEST);
        }
    }
}
