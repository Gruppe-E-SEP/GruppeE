package sep.tippspiel.spiel;

import org.hibernate.sql.Select;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.ArrayList;

public interface SpielRepository extends JpaRepository<Spiel, Long> {

    @Query("SELECT s FROM Spiel s WHERE s.id= :id")
    Spiel findSpielById(@Param("id")Long id);


/*    @Query("SELECT u.email FROM Users u where  u.email= :email")
    String findByEmail(@Param("email") String email);*/


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
