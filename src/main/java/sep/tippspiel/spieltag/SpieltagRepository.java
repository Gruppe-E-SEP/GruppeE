package sep.tippspiel.spieltag;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface SpieltagRepository extends JpaRepository<Spieltag, Long> {

    @Query("SELECT count(s) > 0 FROM Spieltag s where s.tag= :tag")
    boolean isSpieltagPresent (@Param("tag") int tag);

    @Query("SELECT s.id FROM Spieltag s where s.tag= :tag")
    Long getByTag(@Param("tag") int tag);
}
