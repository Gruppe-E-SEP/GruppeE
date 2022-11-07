package sep.tippspiel.systemdatum;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;

public interface SystemDatumRepository extends JpaRepository<SystemDatum, Long> {

    @Query("SELECT sd.date FROM SystemDatum sd WHERE sd.date= :date")
    SystemDatum findByDate(@Param("date") Date date);
}
