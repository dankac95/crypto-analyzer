package pl.daniel.cryptoanalyzer.user;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import pl.daniel.cryptoanalyzer.wallet.Wallet;

import javax.persistence.*;

@RequiredArgsConstructor
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String lastName;

    private  Wallet wallet;


}
