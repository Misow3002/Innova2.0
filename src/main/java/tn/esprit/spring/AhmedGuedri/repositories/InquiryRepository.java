package tn.esprit.spring.repositories;
//create Inquiry Repository
import org.springframework.data.repository.CrudRepository;
import tn.esprit.spring.AhmedGuedri.entities.Inquiry;
public interface InquiryRepository extends CrudRepository<Inquiry, Long> {
}

