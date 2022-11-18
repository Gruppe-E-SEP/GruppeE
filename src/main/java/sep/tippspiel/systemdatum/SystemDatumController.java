package sep.tippspiel.systemdatum;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping(value = "/systemdatum")
@CrossOrigin(origins = "http://localhost:4200")
public class SystemDatumController {

    @Autowired
    SystemDatumService systemDatumService;

/*    @PostMapping(path = "/setdate", produces = "application/json", consumes = "application/json")
    public ResponseEntity<String> setDate(@RequestParam("date")@DateTimeFormat(pattern = "yyyy-MM-dd") Date date) {
        if(systemDatumService.setDate(date)) {
            return new ResponseEntity<>("Datum wurde aktualisiert", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Datum konnte nicht aktualisiert werden", HttpStatus.BAD_REQUEST);
        }
    }*/
}
