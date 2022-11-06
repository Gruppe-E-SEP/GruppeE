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
}
