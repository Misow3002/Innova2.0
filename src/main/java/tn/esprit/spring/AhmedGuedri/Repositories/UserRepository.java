package tn.esprit.spring.AhmedGuedri.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import tn.esprit.spring.AhmedGuedri.entities.Roles;
import tn.esprit.spring.AhmedGuedri.entities.User;

import javax.management.relation.Role;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    @Query("select u from User u where u.Roles = :role")
    List<User> findUserByRoles(Roles role);
}
