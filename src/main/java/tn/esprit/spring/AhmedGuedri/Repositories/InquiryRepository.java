package tn.esprit.spring.AhmedGuedri.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.spring.AhmedGuedri.entities.Inquiry;

public interface InquiryRepository extends JpaRepository<Inquiry, Integer> {
}