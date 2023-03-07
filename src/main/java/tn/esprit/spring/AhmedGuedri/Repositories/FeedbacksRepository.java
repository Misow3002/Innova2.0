package tn.esprit.spring.AhmedGuedri.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.spring.AhmedGuedri.entities.Feedbacks;
import tn.esprit.spring.AhmedGuedri.entities.Products;

@Repository
public interface FeedbacksRepository extends JpaRepository<Feedbacks, Long> {
List<Feedbacks> findByFeedbacksProd(Products product);





}