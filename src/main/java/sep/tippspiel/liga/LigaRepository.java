package sep.tippspiel.liga;

import org.hibernate.sql.Select;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.persistence.criteria.From;
import java.util.Optional;

public interface LigaRepository extends JpaRepository<Liga,Long>{

    @Query("Select l.id From Liga l where l.name = :name")
    Long findByName(@Param("name") String name);

}
