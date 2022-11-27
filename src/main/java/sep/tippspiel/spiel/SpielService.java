package sep.tippspiel.spiel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sep.tippspiel.mannschaft.Mannschaft;

import javax.persistence.criteria.CriteriaBuilder;
import java.text.DecimalFormat;
import java.text.Format;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

    public List<String> findHeimMannschaftScore(String name) {
        List<String> scoreList = this.spielRepository.findHeimMannschaftScore(name);
        for(String score : scoreList) {
            // System.out.println("Spielscore Heim: " + score);
        }
        return scoreList;
    }

    public List<String> findGastmannschaftScore(String name) {
        List<String> scoreList = this.spielRepository.findGastmannschaftScore(name);
        for(String score : scoreList) {
            // System.out.println("Spielscore Gast: " + score);
        }
        return scoreList;
    }

    public String calcVorhersage(String name) {
        //HeimMannschaft
        List<String> scoreListHeim = this.findHeimMannschaftScore(name);
        double vorhersage = 0;
        int counterHeim = 0;
        double vorhersageHeim = 0;

        for(int i = 0; i < scoreListHeim.size(); i++) {
            if(scoreListHeim.get(i) !="") {
                counterHeim++;
                String[] tokens = scoreListHeim.get(i).split("-");
                vorhersageHeim = Integer.parseInt(tokens[0]) - Integer.parseInt(tokens[1]);
            }
        }

        vorhersageHeim = vorhersageHeim / (double)counterHeim;

        //Gast Mannschaft
        List<String> scoreListGast = this.findGastmannschaftScore(name);
        int counterGast = 0;
        double vorhersageGast = 0;

        for(int i = 0; i < scoreListGast.size(); i++) {
            if(scoreListGast.get(i) !="") {
                counterGast++;
                String[] tokens = scoreListGast.get(i).split("-");
                vorhersageGast = Integer.parseInt(tokens[1]) - Integer.parseInt(tokens[0]);
            }
        }

        vorhersageGast = vorhersageGast / (double)counterGast;
        vorhersage = vorhersageHeim + vorhersageGast;

        String prognose = "Fehler! Es konnte keine Statisik berechnet werden";
        if(vorhersage<-1) {
            prognose = "hochwahrscheinlich verliert";
        }
        if(vorhersage>-1 && vorhersage<0) {
            prognose = "wahrscheinlich verliert";
        }
        if(vorhersage==0) {
            prognose = "spielt unentschieden";
        }
        if(vorhersage>0 && vorhersage<1) {
            prognose = "wahrscheinlich gewinnt";
        }
        if(vorhersage>1) {
            prognose = "hochwahrscheinlich gewinnt";
        }
        String vorhersages = new DecimalFormat("0.00").format(vorhersage);
        String ergebnis = "Tipphilfe fuer Team: " + name + " basiert sich auf " + counterHeim + " Heimspiel(e) und " + counterGast + " Gastspiel(e). Das Resultat der Berechung ist: " + vorhersages +
                ". Anhand der Berehnungen laesst sich grob abschaetzen, dass Team " + name + " " + prognose;
        System.out.println(ergebnis);
        return ergebnis;
    }

}
