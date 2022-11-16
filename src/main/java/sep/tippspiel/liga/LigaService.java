package sep.tippspiel.liga;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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


}
