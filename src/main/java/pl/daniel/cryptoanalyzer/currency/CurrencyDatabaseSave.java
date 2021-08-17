package pl.daniel.cryptoanalyzer.currency;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class CurrencyDatabaseSave {

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

    public List<Currency> findAll() {
       return repository.findAll();
    }
}
