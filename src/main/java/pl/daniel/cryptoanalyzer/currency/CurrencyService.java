package pl.daniel.cryptoanalyzer.currency;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CurrencyService {

    private final CurrencyRepository repository;

    @Scheduled(cron = "0 * * * * *")
    public void downloadData() {

        RestTemplate restTemplate = new RestTemplate();
        ApiResponse apiResponse = restTemplate.getForObject("http://api.coinlayer.com/live?access_key=0ba4a0c1e71c448d4ba8de7497b4c4b5", ApiResponse.class);
        System.out.println();

        if (apiResponse.isSuccess()) {

            List<Currency> currencyList = repository.findAll();
            Map<String, Currency> currencyMap = currencyList.stream().collect(Collectors.toMap(Currency::getName, currency -> currency));

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

    private void setAndSaveCurrencyDb(BigDecimal value, Currency currency) {
        currency.setCurrencyValue(value);
        currency.setUpdatedAt(LocalDateTime.now());
        repository.save(currency);
    }

    public List<Currency> findAllCurrencys() {
        return repository.findAll();
    }
}
