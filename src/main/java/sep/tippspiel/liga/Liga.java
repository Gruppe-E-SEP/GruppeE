package sep.tippspiel.liga;

import org.checkerframework.checker.units.qual.C;
import sep.tippspiel.spielplan.Spielplan;

import javax.persistence.*;

public class Liga {

    private Long id;

    private String name;

    private String ligaImage;

    private Spielplan spielplan;

}
