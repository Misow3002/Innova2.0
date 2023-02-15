package tn.esprit.spring.AhmedGuedri.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.spring.AhmedGuedri.entities.Payement;

public interface PayementRepository extends JpaRepository<Payement, Long> {
}