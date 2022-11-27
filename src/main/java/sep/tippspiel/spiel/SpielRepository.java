package sep.tippspiel.spiel;

import org.hibernate.sql.Select;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import sep.tippspiel.mannschaft.Mannschaft;

import java.util.ArrayList;
import java.util.List;

public interface SpielRepository extends JpaRepository<Spiel, Long> {

    @Query("SELECT s FROM Spiel s WHERE s.id= :id")
    Spiel findSpielById(@Param("id")Long id);


    @Query("SELECT s.score FROM Spiel s join Mannschaft m1 on s.mannschaft.id=m1.id and m1.name= :name")
    List<String> findHeimMannschaftScore(@Param(value = "name") String name);

    @Query("SELECT s.score FROM Spiel s join Mannschaft m2 on s.mannschaft2.id=m2.id and m2.name= :name")
    List<String> findGastmannschaftScore(@Param(value = "name") String name);



/*    @Query("SELECT  t.tag, m1.name, s.score, m2.name FROM Spiel s JOIN Mannschaft m1 ON s.Mannschaft=m1.ID " +
            "JOIN Mannschaft m2 ON s.Mannschaft=m2.ID  " +
            "JOIN Spieltag t ON s.SPIELTAG_ID=t.ID  " +
            "ORDER BY t.tag");*/

 /*   SELECT  t.tag, m1.name, s.score, m2.name
    FROM SPIEL s
    JOIN MANNSCHAFT m1 ON s.MANNSCHAFT_ID=m1.ID
    JOIN MANNSCHAFT m2 ON s.MANNSCHAFT_ID2=m2.ID
    JOIN SPIELTAG t ON s.SPIELTAG_ID=t.ID
    ORDER BY t.tag*/

}
