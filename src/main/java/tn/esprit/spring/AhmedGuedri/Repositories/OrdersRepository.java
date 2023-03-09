package tn.esprit.spring.AhmedGuedri.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;
import tn.esprit.spring.AhmedGuedri.entities.Orders;
@Repository
public interface OrdersRepository extends JpaRepository<Orders,Long> {
}

