package tn.esprit.spring.AhmedGuedri.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.spring.AhmedGuedri.entities.Invoices;

public interface invoiceRepo extends JpaRepository<Invoices,Long> {
}
