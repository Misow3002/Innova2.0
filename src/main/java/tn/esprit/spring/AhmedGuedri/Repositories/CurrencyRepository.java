package tn.esprit.spring.AhmedGuedri.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.spring.AhmedGuedri.entities.Currency;

public interface CurrencyRepository extends JpaRepository<Currency, Long> {
}