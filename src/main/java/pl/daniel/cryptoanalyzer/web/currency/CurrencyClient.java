package pl.daniel.cryptoanalyzer.web.currency;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import pl.daniel.cryptoanalyzer.currency.ApiResponse;
import pl.daniel.cryptoanalyzer.currency.Currency;
import pl.daniel.cryptoanalyzer.currency.CurrencyDatabaseSave;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class CurrencyClient {

    // TODO-purban: Klasa ma za duzo odpowiedzialnosci - powinna tylko obslugiwac komunikacje z API, nie powonna w ogole miec zaleznosci do repo
    public final RestTemplate restTemplate;
    private final CurrencyDatabaseSave currencyDatabaseSave;

    @Value("${crypto.catalog.query.apiKey}")
    private String apiKey;


    public String getDateForRates(LocalDate date, String targetCurrency) {
        return restTemplate.getForObject("http://api.coinlayer.com/{date}?access_key={apiKey}&symbols={targetCurrency}"
                , String.class, date, apiKey, targetCurrency);
    }

    public void communicationWithApi() {

        ApiResponse apiResponse = restTemplate.getForObject("http://api.coinlayer.com/live?access_key=" + apiKey
                , ApiResponse.class);

        if (apiResponse.isSuccess()) {

            List<Currency> currencyList = currencyDatabaseSave.findAll();
            Map<String, Currency> currencyMap = currencyList.stream().collect(Collectors.toMap(Currency::getName, currency -> currency));

            currencyDatabaseSave.setApiResponseToCurrency(apiResponse, currencyMap);
        }
    }
}