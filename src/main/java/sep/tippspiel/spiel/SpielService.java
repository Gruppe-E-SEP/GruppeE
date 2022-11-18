package sep.tippspiel.spiel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class SpielService {

    @Autowired
    SpielRepository spielRepository;

    public boolean setSpielDate(Long id, Date date) {
        try {
            Spiel spiel = this.spielRepository.getReferenceById(id);
            spiel.setDate(date);
            this.spielRepository.save(spiel);
            return true;
        } catch (Exception e) {
            System.err.println("Spieldatum konnte nicht aktualisiert werden" + e.getStackTrace());
            return false;
        }
    }
}
