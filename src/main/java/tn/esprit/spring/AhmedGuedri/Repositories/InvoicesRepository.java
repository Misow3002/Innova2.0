package tn.esprit.spring.AhmedGuedri.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.spring.AhmedGuedri.entities.Invoices;

public interface InvoicesRepository extends JpaRepository<Invoices, Long> {
}