package tn.esprit.spring.AhmedGuedri.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.spring.AhmedGuedri.entities.FeedObject;

public interface FeedObjectRepository extends JpaRepository<FeedObject, Integer> {
}