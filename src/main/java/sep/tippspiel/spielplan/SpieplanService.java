package sep.tippspiel.spielplan;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sep.tippspiel.mannschaft.MannschaftRepository;
import sep.tippspiel.mannschaft.MannschaftService;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

@Service
public class SpieplanService {

    @Autowired
    SpielplanRepository spielplanRepository;
    MannschaftRepository mannschaftRepository;

    public Spielplan csvEinlesen(File csv) {
        String line = "";
        final String delimiter = ",";

        try {
            FileReader fileReader = new FileReader(csv);
            BufferedReader reader = new BufferedReader(fileReader);
            while ((line = reader.readLine()) !=null) {

                String[] token = line.split(delimiter);
                //ToDo DB Entity Zuordnung
                //ToDo Mannschaft JPA searchByName;
                //ToDo

                System.out.println(token[0] + " ! " + token[1] + " !" + token[2] + " ! " + token[3] + " ! " + token[4]);

            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        Spielplan spielplan = new Spielplan();
        return spielplan;
    }
}
