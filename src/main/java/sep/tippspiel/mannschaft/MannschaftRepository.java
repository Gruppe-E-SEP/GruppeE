package sep.tippspiel.mannschaft;

import org.hibernate.sql.Select;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MannschaftRepository extends JpaRepository<Mannschaft, Long> {


    @Query("SELECT count(t) > 0 FROM Mannschaft t where t.name= :name")
    boolean isMannschaftPresent (@Param("name") String name);

    @Query("SELECT t.id FROM Mannschaft t where t.name= :name")
    Long findByName(@Param("name") String name);

}
