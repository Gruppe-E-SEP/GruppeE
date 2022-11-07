package sep.tippspiel.mannschaft;

import org.hibernate.sql.Select;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MannschaftRepository extends JpaRepository<Mannschaft, Long> {

}
