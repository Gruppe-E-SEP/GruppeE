package sep.tippspiel.spielplan;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sep.tippspiel.mannschaft.MannschaftRepository;
import sep.tippspiel.mannschaft.MannschaftService;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

@Service
public class SpieplanService {

    @Autowired
    SpielplanRepository spielplanRepository;
    MannschaftRepository mannschaftRepository;

}
