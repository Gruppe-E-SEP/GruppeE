package sep.tippspiel.mannschaft;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class MannschaftService {

    @Autowired
    private MannschaftRepository mannschaftRepository;


}
