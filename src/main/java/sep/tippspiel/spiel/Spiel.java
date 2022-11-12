package sep.tippspiel.spiel;

import sep.tippspiel.mannschaft.Mannschaft;
import sep.tippspiel.spieltag.Spieltag;

import javax.persistence.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@Entity
public class Spiel {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "score")
    private String score;

    @Column(name = "date")
    private Date date;

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @ManyToOne
    @JoinColumn(name = "spieltag_id")
    private Spieltag spieltag;

/*    @OneToMany(fetch = FetchType.EAGER, mappedBy = "spiel")
    private List<Spieltag> spieltagList = new ArrayList<>();*/


    @ManyToOne
    @JoinColumn(name = "mannschaft_id")
    private Mannschaft mannschaft;

    @ManyToOne
    @JoinColumn(name = "mannschaft_id2")
    private Mannschaft mannschaft2;


    public Spiel(){};

    public Spiel(Mannschaft mannschaft, Mannschaft mannschaft2, String date, String score, Spieltag spieltag) throws ParseException {
        this.mannschaft = mannschaft;
        this.mannschaft2 = mannschaft2;
        SimpleDateFormat formatter = new SimpleDateFormat("EEE MMM dd yyyy", Locale.ENGLISH);
        String dateInString = date;
        Date dateD = formatter.parse(dateInString);
        this.date = dateD;
        this.score = score;
        this.spieltag = spieltag;

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
