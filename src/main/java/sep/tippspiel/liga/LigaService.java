package sep.tippspiel.liga;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Service
public class LigaService {

    @Autowired
    LigaRepository ligaRepository;

    public boolean setLigaName(String oldName, String newName) {
        Long id = this.ligaRepository.findByName(oldName);
        try {
            Liga liga = this.ligaRepository.getReferenceById(id);
            liga.setName(newName);
            this.ligaRepository.save(liga);
            return  true;
        } catch (Exception e) {
            e.getStackTrace();
            return false;
        }

    }

    public boolean setLigaNameById(Long id, String newName) {
        try {
            Liga liga = this.ligaRepository.getReferenceById(id);
            liga.setName(newName);
            this.ligaRepository.save(liga);
            return true;
        } catch (Exception e) {
            e.getStackTrace();
            return false;
        }

    }

    public boolean setLigaImage(Long id, String ligaImage) {
        try {
            Liga liga = this.ligaRepository.getReferenceById(id);
            liga.setName(ligaImage);
            this.ligaRepository.save(liga);
            return true;
        } catch (Exception e) {
            System.out.println("Liga Image konnte nicht gesetzt werden" + e.getStackTrace());
            return false;
        }
    }

    public boolean istImageFormat(MultipartFile file) {
        String TYPE = "image/jpeg";

        if (!TYPE.equals(file.getContentType())) {
            return false;
        }
        return true;
    }


}
