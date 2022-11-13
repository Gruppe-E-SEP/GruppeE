package sep.tippspiel.systemadministrator;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SystemadministratorRepository extends JpaRepository<Systemadministrator, Long> {

    @Query("SELECT sa.email FROM Systemadministrator sa where sa.email= :email")
    String findByEmail(@Param("email") String email);

    @Query("SELECT sa FROM Systemadministrator sa where sa.vorname= : vorname")
    List<Systemadministrator> findByName(@Param("vorname") String vorname);

/*    SELECT  l.name, t.tag, m1.name, s.score, m2.name
    FROM SPIEL s
    JOIN MANNSCHAFT m1 ON s.MANNSCHAFT_ID=m1.ID
    JOIN MANNSCHAFT m2 ON s.MANNSCHAFT_ID2=m2.ID
    JOIN SPIELTAG t ON s.SPIELTAG_ID=t.ID
    JOIN SPIELPLAN p ON p.ID=t.SPIELPLAN_ID
    JOIN LIGA l ON l.SPIELPLAN_ID=p.ID

    ORDER BY t.tag*/
}
