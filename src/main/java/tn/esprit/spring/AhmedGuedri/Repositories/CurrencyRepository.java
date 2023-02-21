//create Currency Repository   
package tn.esprit.spring.AhmedGuedri.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import tn.esprit.spring.AhmedGuedri.entities.Currency;
import tn.esprit.spring.AhmedGuedri.entities.CurrencyType;

public interface CurrencyRepository extends JpaRepository<Currency, Long> {

    Currency findByCurrencyType(CurrencyType currencyType);
}