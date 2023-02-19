//create Currency Repository   
package tn.esprit.spring.AhmedGuedri.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import tn.esprit.spring.AhmedGuedri.entities.Currency;  

public interface CurrencyRepository extends CrudRepository<Currency, Long> {

    Currency findByCurrencyType(String CurrencyType);
}