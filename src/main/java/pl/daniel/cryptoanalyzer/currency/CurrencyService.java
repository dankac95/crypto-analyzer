package pl.daniel.cryptoanalyzer.currency;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import pl.daniel.cryptoanalyzer.web.currency.CurrencyClient;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CurrencyService {

    private final CurrencyClient currencyClient;
    private final CurrencyFetchScheduler currencyFetchScheduler;
    private final CurrencyRepository repository;

    @Value("${crypto.catalog.query.targerCurrency}")
    private String targetCurrency;

    public void saveRatesTooDatabase() {
        currencyFetchScheduler.downloadData();
    }


    public String downloadHistoryData(LocalDate date) {
        // TODO: TargetCurrency powinno byc czytane z konfiguracje
        return currencyClient.getDateForRates(date, targetCurrency);
    }

    public List<Currency> finadAll() {
        return repository.findAll();
    }
}
