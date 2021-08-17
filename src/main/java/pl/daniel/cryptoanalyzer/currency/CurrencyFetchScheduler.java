package pl.daniel.cryptoanalyzer.currency;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import pl.daniel.cryptoanalyzer.web.currency.CurrencyClient;

@RequiredArgsConstructor
@Component
public class CurrencyFetchScheduler {

    private final CurrencyClient currencyClient;

    @Scheduled(cron = "0 * * * * *")
    public void downloadData() {

        currencyClient.communicationWithApi();
    }


}
