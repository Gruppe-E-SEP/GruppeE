package sep.tippspiel.mannschaft;

import sep.tippspiel.spiel.Spiel;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Mannschaft implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "spiel_id")
    private Spiel spiel;

    public Mannschaft() {};

    public Mannschaft(String name)  {
        this.name = name;
    }

    @Override
    public String toString() {return "Name: " + name;}

    public Spiel getSpiel() {
        return spiel;
    }

    public void setSpiel(Spiel spiel) {
        this.spiel = spiel;
    }
}
