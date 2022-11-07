package sep.tippspiel.spieltag;

import sep.tippspiel.spiel.Spiel;
import sep.tippspiel.spielplan.Spielplan;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Spieltag {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;


    @OneToMany(fetch = FetchType.EAGER, mappedBy = "spieltag")
    private List<Spiel> spielList = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "spielplan_id")
    private Spielplan spielplan;

    public Spielplan getSpielplan() {
        return spielplan;
    }

    public void setSpielplan(Spielplan spielplan) {
        this.spielplan = spielplan;
    }
}
