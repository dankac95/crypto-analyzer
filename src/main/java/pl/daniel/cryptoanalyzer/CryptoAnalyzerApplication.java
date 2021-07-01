package pl.daniel.cryptoanalyzer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

@SpringBootApplication
public class CryptoAnalyzerApplication {

    public static void main(String[] args) {
        SpringApplication.run(CryptoAnalyzerApplication.class, args);
    }

}
