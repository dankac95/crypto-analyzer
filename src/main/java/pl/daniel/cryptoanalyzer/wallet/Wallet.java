package pl.daniel.cryptoanalyzer.wallet;

import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import pl.daniel.cryptoanalyzer.currency.Currency;

import java.util.Map;

@RequiredArgsConstructor
@Data
@Getter
@Setter
public class Wallet {

    private transient Map<String, Currency> wallet;
}
