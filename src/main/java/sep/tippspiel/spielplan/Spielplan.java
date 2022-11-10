package sep.tippspiel.spielplan;

import org.checkerframework.checker.units.qual.C;
import org.springframework.beans.factory.annotation.Autowired;
import sep.tippspiel.liga.Liga;
import sep.tippspiel.mannschaft.Mannschaft;
import sep.tippspiel.mannschaft.MannschaftRepository;
import sep.tippspiel.mannschaft.MannschaftService;
import sep.tippspiel.spieltag.Spieltag;

import javax.persistence.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Spielplan {


    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

/*    @ManyToOne
    @JoinColumn(name = "spieltag")
    private Spieltag spieltag;*/

    public Spielplan() {};

    @OneToOne(mappedBy = "spielplan")
    private Liga liga;


    @OneToMany(fetch = FetchType.EAGER, mappedBy = "spielplan")
    private List<Spieltag> spieltagList = new ArrayList<>();


}
