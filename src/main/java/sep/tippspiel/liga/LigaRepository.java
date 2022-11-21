package sep.tippspiel.liga;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface LigaRepository extends JpaRepository<Liga,Long>{

    @Query("Select l.id From Liga l where l.name = :name")
    Long findByName(@Param("name") String name);

    boolean existsByName(String name);



}
