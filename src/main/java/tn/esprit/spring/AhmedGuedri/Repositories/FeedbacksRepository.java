package tn.esprit.spring.AhmedGuedri.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.spring.AhmedGuedri.entities.Feedbacks;

public interface FeedbacksRepository extends JpaRepository<Feedbacks, Integer> {
}