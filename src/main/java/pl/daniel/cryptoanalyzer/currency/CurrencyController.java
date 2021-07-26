package pl.daniel.cryptoanalyzer.currency;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.reactive.function.client.WebClient;

@Controller
@RequiredArgsConstructor
@RequestMapping("/")
public class CurrencyController {

    private final CurrencyService currencyService;

    @GetMapping
    public void run() {
        currencyService.downloadData();
    }





}
