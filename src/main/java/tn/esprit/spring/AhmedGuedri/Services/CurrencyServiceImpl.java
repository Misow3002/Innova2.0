package tn.esprit.spring.AhmedGuedri.Services;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.AhmedGuedri.Repositories.CurrencyRepository;
import tn.esprit.spring.AhmedGuedri.entities.Currency;
@Service
public class CurrencyServiceImpl implements ICurrencyService {
    @Autowired
    CurrencyRepository currencyRepository;
    @Override
    public List<Currency> retrieveAllCurrencies() {


        List<Currency> currencies = (List<Currency>) currencyRepository.findAll();
        return currencies;
    }
    @Override
    public Currency addCurrency(Currency c) {
        return currencyRepository.save(c);
    }
    @Override
    public void deleteCurrency(String id) {
        currencyRepository.deleteById(Long.parseLong(id));
    }
    @Override

    public Currency updateCurrency(Currency c) {
        return currencyRepository.save(c);
    }
    @Override

    public Currency retrieveCurrency(String id) {
        return currencyRepository.findById(Long.parseLong(id)).get();
    }
}


