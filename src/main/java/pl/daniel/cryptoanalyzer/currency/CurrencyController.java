package pl.daniel.cryptoanalyzer.currency;

import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/currency")
public class CurrencyController {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    private final CurrencyService currencyService;

    @GetMapping("/list")
    public List<Currency> printAllRates() {
        //  TODO: Zwroc liste aktualnych kursow z DB
        return currencyService.finadAll();
    }

    @GetMapping("/history")
    public String findInTimeByName(@RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        // TODO: Data dla ktorej chcesz pobrac historie powinna byc przyjmowana w parametrze (@RequestParam, LocalDate)
        return currencyService.downloadHistoryData(date);
    }
}