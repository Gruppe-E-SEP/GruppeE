package sep.tippspiel.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository<Users, Long>{

    @Query("SELECT u.email FROM Users u where  u.email= :email")
    String findByEmail(@Param("email") String email);

    @Query("SELECT u FROM Users u WHERE u.vorname= :vorname ")
    List<Users> findByName(@Param("vorname") String vorname);

    @Query("SELECT u.id FROM Users u where  u.email= :email")
    Long findUserIdByEmail(@Param("email") String email);

}
