package pl.daniel.cryptoanalyzer.currency;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Map;

@Getter
@Setter
public class ApiResponse {

    private String target;
    private boolean success;
    private Map<String, BigDecimal> rates;

}
