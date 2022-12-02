package sep.tippspiel.tipprunde;

import sep.tippspiel.liga.Liga;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Tipprunde implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    String name;

    @Column(name = "status")
    Status status;

    @OneToOne
    @JoinColumn(name = "liga_id")
    private Liga liga;

    public Tipprunde() {}

    public Tipprunde(String name, Status status) {
        this.name = name;
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public String toString() {return "Name:" + name + " Status: " + status;}

}
