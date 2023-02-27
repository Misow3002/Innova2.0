package tn.esprit.spring.AhmedGuedri.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.spring.AhmedGuedri.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
