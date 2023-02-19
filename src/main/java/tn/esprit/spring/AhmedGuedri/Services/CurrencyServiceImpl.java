package tn.esprit.spring.AhmedGuedri.Services;
import java.io.IOException;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
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
    public Currency getCurrencyByName(String CurrencyType) {
        return currencyRepository.findByCurrencyType(CurrencyType);
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
    private static final String CURRENCY_RATES_URL = "https://example.com/currency-rates";
    @Scheduled(fixedRate = 3600000) // run every hour
    public void updateCurrencyRates() {
        try {
            Document doc = Jsoup.connect(CURRENCY_RATES_URL).get();
            Elements elements = doc.select("table tr");
            for (Element element : elements) {
                String name = element.select("td:nth-child(1)").text();
                String rateText = element.select("td:nth-child(2)").text();
                Float rate = Float.parseFloat(rateText);
                Currency currency = currencyRepository.findByCurrencyType(name);
                currency.setExchangeRate(rate);
                currencyRepository.save(currency);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

    /*@Override
    public String getExchangeRate(String CurrencyType) {
        try {
            // Build the URL for the exchange rate website
            String url = "https://www.x-rates.com/calculator/?from=" + CurrencyType.toUpperCase()+"&to=TND";

            // Scrape the exchange rate from the website
            Document doc = Jsoup.connect(url).get();
            String exchangeRate = doc.select("#cc-ratebox").first().text();

            return "The exchange rate for " + CurrencyType.toUpperCase() + " is " + exchangeRate;
            return currencyRepository.find
        } catch (Exception e) {
            return "Error retrieving exchange rate for " + CurrencyType.toUpperCase();
        }
    }
    //create a web scraping function for an exchange rate conversion*/





