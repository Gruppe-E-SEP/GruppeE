package sep.tippspiel.systemdatum;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class SystemDatumService {
    @Autowired
    private SystemDatumRepository systemDatumRepository;

    public SystemDatum findByDate(Date date) {
        return this.systemDatumRepository.findByDate(date);
    }

    public boolean setDate(Date date) {

        try {
            SystemDatum systemDatum = new SystemDatum(date);
            this.systemDatumRepository.deleteAll();
            this.systemDatumRepository.save(systemDatum);
            return true;
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return false;
        }
    }


}
