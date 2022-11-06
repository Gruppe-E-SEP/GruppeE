package sep.tippspiel.spielplan;

import sep.tippspiel.spieltag.Spieltag;

import java.io.File;
import java.util.List;

public class Spielplan {

    private List<Spieltag> spieltagList;

    public Spielplan csvEinlesen(File csv) {
        Spielplan spielplan = new Spielplan();
        return spielplan;
    }
}
