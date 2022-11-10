package sep.tippspiel.spieltag;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface SpieltagRepository extends JpaRepository<Spieltag, Long> {

    @Query("SELECT s.tag FROM Spieltag s where s.tag= :tag")
    boolean isSpieltagPresent (@Param("tag") int tag);
}
