package sep.tippspiel.spielplan;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface SpielplanRepository extends JpaRepository<Spielplan, Long>{
    @Query("SELECT max(s.id) FROM Spielplan s")
    Long getMaxId();
}
