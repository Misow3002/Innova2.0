package tn.esprit.spring.AhmedGuedri.Services;
//create Curency Interface
import java.util.List;
import tn.esprit.spring.AhmedGuedri.entities.Currency;
public interface ICurrencyService {
    public List<Currency> retrieveAllCurrencies();
    public Currency addCurrency(Currency c);
    public void deleteCurrency(String id);
    public Currency updateCurrency(Currency c);
    public Currency retrieveCurrency(String id);
}
