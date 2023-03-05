package tn.esprit.spring.AhmedGuedri.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tn.esprit.spring.AhmedGuedri.entities.User;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    User findByEmailEquals(String email);

        @Query("SELECT count(u) FROM User u WHERE u.Enabled = true")
    public int NumberOfSubs();

        @Query("select e,h from User e, HashedPWD h where e.email=:Email and e.hashedPWD.PassId=h.PassId")
    public List<Object[]> Authentification(@Param("Email") String email);
}
