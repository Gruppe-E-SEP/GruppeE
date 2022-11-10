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

    @Column(name = "score")
    private String score;

    @Column(name = "date")
    private String date;

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
/*    @ManyToOne
    @JoinColumn(name = "spieltag_id")
    private Spieltag spieltag;*/

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "spiel")
    private List<Spieltag> spieltagList = new ArrayList<>();


    @ManyToOne
    @JoinColumn(name = "mannschaft_id")
    private Mannschaft mannschaft;

    @ManyToOne
    @JoinColumn(name = "mannschaft_id2")
    private Mannschaft mannschaft2;


    public Spiel(){};

    public Spiel(Mannschaft mannschaft, Mannschaft mannschaft2, String date, String score) {
        this.mannschaft = mannschaft;
        this.mannschaft2 = mannschaft2;
        this.date = date;
        this.score = score;
    }

/*  @OneToMany(fetch = FetchType.EAGER, mappedBy = "spiel")
    private List<Mannschaft> mannschaftList = new ArrayList<>();*/

/*
    public Spieltag getSpieltag() {
        return spieltag;
    }

    public void setSpieltag(Spieltag spieltag) {
        this.spieltag = spieltag;
    }
*/


}
