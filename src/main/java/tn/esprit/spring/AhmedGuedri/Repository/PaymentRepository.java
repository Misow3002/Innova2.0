package tn.esprit.spring.AhmedGuedri.Repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.spring.AhmedGuedri.entities.Orders;
import tn.esprit.spring.AhmedGuedri.entities.Payment;

import java.util.List;


@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {

    List<Payment> findAll();
}
