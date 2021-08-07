package pl.daniel.cryptoanalyzer.web.currency;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import pl.daniel.cryptoanalyzer.currency.ApiResponse;
import pl.daniel.cryptoanalyzer.currency.Currency;
import pl.daniel.cryptoanalyzer.currency.CurrencyRepository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class CurrencyClient {

    public static final RestTemplate restTemplate = new RestTemplate();
    public final String API_KEY = "0ba4a0c1e71c448d4ba8de7497b4c4b5";
    private final CurrencyRepository repository;

    public void setApiResponseToCurrency(ApiResponse apiResponse, Map<String, Currency> currencyMap) {

        for (Map.Entry<String, BigDecimal> stringBigDecimalEntry : apiResponse.getRates().entrySet()) {
            String currencyName = stringBigDecimalEntry.getKey();

            if (currencyMap.containsKey(currencyName)) {
                Currency currencyDb = currencyMap.get(currencyName);
                if (currencyDb.getCurrencyValue().compareTo(stringBigDecimalEntry.getValue()) != 0) {
                    updateCurrency(currencyName, stringBigDecimalEntry.getValue(), currencyDb);
                }
            } else {
                updateCurrency(currencyName, stringBigDecimalEntry.getValue(), null);
            }
        }
    }

    private void setAndSaveCurrencyDb(BigDecimal value, Currency currency) {
        currency.setCurrencyValue(value);
        currency.setUpdatedAt(LocalDateTime.now());
        repository.save(currency);
    }

    private void updateCurrency(String name, BigDecimal value, Currency currency) {

        if (currency != null) {
            setAndSaveCurrencyDb(value, currency);
        } else {
            currency = new Currency();
            currency.setName(name);
            setAndSaveCurrencyDb(value, currency);
        }
    }

    public String getDateForRates(String date, String targetCurrency) {
        return restTemplate.getForObject("http://api.coinlayer.com/{date}?access_key={apiKey}&symbols={targetCurrency}"
                , String.class, date, API_KEY, targetCurrency);
    }
}