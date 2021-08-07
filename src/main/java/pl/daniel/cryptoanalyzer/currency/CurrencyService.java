package pl.daniel.cryptoanalyzer.currency;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import pl.daniel.cryptoanalyzer.web.currency.CurrencyClient;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CurrencyService {

    private final CurrencyClient currencyClient;
    private final CurrencyRepository repository;
    private RestTemplate restTemplate = new RestTemplate();

    @Scheduled(cron = "0 * * * * *")
    public void downloadData() {

        ApiResponse apiResponse = restTemplate.getForObject("http://api.coinlayer.com/live?access_key=0ba4a0c1e71c448d4ba8de7497b4c4b5"
                , ApiResponse.class);

        if (apiResponse.isSuccess()) {

            List<Currency> currencyList = repository.findAll();
            Map<String, Currency> currencyMap = currencyList.stream().collect(Collectors.toMap(Currency::getName, currency -> currency));

            currencyClient.setApiResponseToCurrency(apiResponse, currencyMap);
        }
    }

    public String downloadHistoryData() {
        return currencyClient.getDateForRates("2020-08-15", "ACT");
    }
}