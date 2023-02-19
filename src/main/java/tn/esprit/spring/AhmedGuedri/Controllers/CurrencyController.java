package tn.esprit.spring.AhmedGuedri.Controllers;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import tn.esprit.spring.AhmedGuedri.Services.ICurrencyService;
import tn.esprit.spring.AhmedGuedri.entities.Currency;
@RestController
public class CurrencyController {
    @Autowired
    ICurrencyService ics;
    @GetMapping("/retrieve-all-Currencies")
    public List<Currency> getCurrencies() {
        List<Currency> list = ics.retrieveAllCurrencies();
        return list;
    }
    @PostMapping("/add-Currency")
    public Currency addCurrency(@RequestBody Currency c) {
        return ics.addCurrency(c);
    }
    @DeleteMapping("/remove-Currency/{Currency-id}")
    public void removeCurrency(@PathVariable("Currency-id") String id) {
        ics.deleteCurrency(id);
    }
    @PutMapping("/modify-Currency")
    public Currency modifyCurrency(@RequestBody Currency c) {
        return ics.updateCurrency(c);
    }
    @GetMapping("/retrieve-Currency/{Currency-id}")
    public Currency retrieveCurrency(@PathVariable("Currency-id") String id) {
        return ics.retrieveCurrency(id);
    }
    }
}


