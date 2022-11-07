package sep.tippspiel.mannschaft;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
public class Mannschaft implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    public Mannschaft() {};

    public Mannschaft(String name)  {
        this.name = name;
    }

    @Override
    public String toString() {return "Name: " + name;}
}
