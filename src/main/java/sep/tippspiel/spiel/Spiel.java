package sep.tippspiel.spiel;

import sep.tippspiel.mannschaft.Mannschaft;
import sep.tippspiel.spieltag.Spieltag;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Spiel {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "spieltag_id")
    private Spieltag spieltag;

    public Spiel(){};

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "spiel")
    private List<Mannschaft> mannschaftList = new ArrayList<>();

    public Spieltag getSpieltag() {
        return spieltag;
    }

    public void setSpieltag(Spieltag spieltag) {
        this.spieltag = spieltag;
    }

/*    private Mannschaft heimmannschaft;
    private Mannschaft auswärtsmannschaft;*/
}
