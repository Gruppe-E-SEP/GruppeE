package sep.tippspiel.liga;

import org.checkerframework.checker.units.qual.C;
import sep.tippspiel.spielplan.Spielplan;

import javax.persistence.*;

@Entity
public class Liga {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "ligaImage")
    private String ligaImage;

    public Spielplan getSpielplan() {
        return spielplan;
    }

    public void setSpielplan(Spielplan spielplan) {
        this.spielplan = spielplan;
    }

    @OneToOne
    @JoinColumn(name = "spielplan_id")
    private Spielplan spielplan;

    public Liga(){};

    public Liga(String name, Spielplan spielplan, String ligaImage) {
        this.name = name;
        this.spielplan = spielplan;
        this.ligaImage = ligaImage;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLigaImage() {
        return ligaImage;
    }

    public void setLigaImage(String ligaImage) {
        this.ligaImage = ligaImage;
    }
}
