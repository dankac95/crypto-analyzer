package pl.daniel.cryptoanalyzer.currency;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/currency")
public class CurrencyController {

    private final CurrencyService currencyService;

    @GetMapping("/list")
    public void run() {
        currencyService.downloadData();
    }

    @GetMapping("/history")
    public String findInTimeByName() {
        return currencyService.downloadHistoryData();
    }
}