package sep.tippspiel.liga;

import org.checkerframework.checker.units.qual.C;
import sep.tippspiel.spielplan.Spielplan;

import javax.persistence.*;

@Entity
public class Liga {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "ligaImage")
    private String ligaImage;

    @OneToOne
    @JoinColumn(name = "spielplan_id")
    private Spielplan spielplan;

    public Liga(){};

    public Liga(String name, Spielplan spielplan, String ligaImage) {
        this.name = name;
        this.spielplan = spielplan;
        this.ligaImage = ligaImage;
    }



}
