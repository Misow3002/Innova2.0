package tn.esprit.spring.AhmedGuedri.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.spring.AhmedGuedri.entities.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
}
