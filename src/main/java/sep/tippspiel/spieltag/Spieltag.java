package sep.tippspiel.spieltag;

import org.springframework.beans.factory.annotation.Autowired;
import sep.tippspiel.mannschaft.Mannschaft;
import sep.tippspiel.spiel.Spiel;
import sep.tippspiel.spiel.SpielService;
import sep.tippspiel.spielplan.Spielplan;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Spieltag {


    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "tag")
    private int tag;

    public int getTag() {
        return tag;
    }

    public void setTag(int tag) {
        this.tag = tag;
    }
/*    @ManyToOne
    @JoinColumn(name = "spiel_id")
    private Spiel spiel;*/

/*    @OneToMany(fetch = FetchType.EAGER, mappedBy = "spieltag")
    private List<Spielplan> spielplanList = new ArrayList<>();*/


    @OneToMany(mappedBy = "spieltag")
    private List<Spiel> spielList = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "spielplan_id")
    private Spielplan spielplan;

    /*public Spielplan getSpielplan() {
        return spielplan;
    }

    public void setSpielplan(Spielplan spielplan) {
        this.spielplan = spielplan;
    }*/

    public Spieltag() {};

    public Spielplan getSpielplan() {
        return spielplan;
    }

    public void setSpielplan(Spielplan spielplan) {
        this.spielplan = spielplan;
    }

    public Spieltag(int tag, Spielplan spielplan) {
        this.tag = tag;
        this.spielplan = spielplan;
    }



}
