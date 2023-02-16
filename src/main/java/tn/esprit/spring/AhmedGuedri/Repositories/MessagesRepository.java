package tn.esprit.spring.AhmedGuedri.Repositories;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.spring.AhmedGuedri.entities.Message;

@Repository
public interface MessagesRepository extends CrudRepository<Message,Long> {
}
