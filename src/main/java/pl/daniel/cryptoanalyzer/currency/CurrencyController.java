package pl.daniel.cryptoanalyzer.currency;

import lombok.RequiredArgsConstructor;
import org.dom4j.rule.Mode;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/currency")
public class CurrencyController {

    private final CurrencyService currencyService;

    @GetMapping
    public void run() {
        currencyService.downloadData();
    }

    @GetMapping("/list")
    public String currencyList(Model model) {
        List<Currency> currencies = currencyService.findAllCurrencys();
        model.addAttribute("currencies", currencies);
        return "currencies-list";
    }
}
