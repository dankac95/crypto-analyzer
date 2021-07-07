package pl.daniel.cryptoanalyzer.wallet;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import pl.daniel.cryptoanalyzer.currency.Currency;

import java.util.List;

@RequiredArgsConstructor
@Data
public class Wallet {

    private List<Currency> cash;
}
