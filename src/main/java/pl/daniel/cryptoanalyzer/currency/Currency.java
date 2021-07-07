package pl.daniel.cryptoanalyzer.currency;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@RequiredArgsConstructor
@Data
@Entity
@Table(name = "currency")
public class Currency {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String name;

    @Column
    private BigDecimal valueCurrency;
}
