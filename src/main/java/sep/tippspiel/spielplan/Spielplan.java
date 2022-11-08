package sep.tippspiel.spielplan;

import org.checkerframework.checker.units.qual.C;
import sep.tippspiel.liga.Liga;
import sep.tippspiel.spieltag.Spieltag;

import javax.persistence.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Spielplan {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "spielplan")
    private List<Spieltag> spieltagList = new ArrayList<>();

    public Spielplan csvEinlesen(File csv) {
        Spielplan spielplan = new Spielplan();
        return spielplan;
    }
}
