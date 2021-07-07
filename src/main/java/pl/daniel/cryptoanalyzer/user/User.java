package pl.daniel.cryptoanalyzer.user;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import pl.daniel.cryptoanalyzer.wallet.Wallet;

@RequiredArgsConstructor
@Data
public class User {

    private String name;
    private String lastName;
    private Wallet wallet;
}
