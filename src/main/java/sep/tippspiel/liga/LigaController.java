package sep.tippspiel.liga;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

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

    @PutMapping(path = "/saveliganamebyid", produces = "application/json", consumes = "application/json")
    public ResponseEntity<String> saveLigaName(@RequestParam Long id,@RequestParam String newName) {
        if(this.ligaService.setLigaNameById(id, newName)) {
            return new ResponseEntity<>("Liganame wurde erfolgreich geändert", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Liganame konnte nicht geändert werden", HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping(path = "/setligaimage")
    public ResponseEntity<String> setLigaImage(@RequestParam Long id,@RequestParam("image")MultipartFile multipartFile) {

        if(this.ligaService.istImageFormat(multipartFile)) {
                String fileName = "src/main/resources/image"+id.toString()+".jpeg";
            if(this.ligaService.setLigaImage(id,fileName)) {
                File file = new File(fileName);

                try (OutputStream os = new FileOutputStream(file)) {
                    os.write(multipartFile.getBytes());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                return new ResponseEntity<>("Image wurde gespeichert", HttpStatus.OK);
        } else {
                return new ResponseEntity<>("Image konnte nicht gespeichert werden", HttpStatus.BAD_REQUEST);
            }

        }
        return new ResponseEntity<>("Es darf nur JPEG Format verwendet werden", HttpStatus.BAD_REQUEST);
    }


}
