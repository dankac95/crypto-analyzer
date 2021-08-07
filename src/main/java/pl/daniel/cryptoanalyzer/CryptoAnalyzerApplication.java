package pl.daniel.cryptoanalyzer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class CryptoAnalyzerApplication {

    public static void main(String[] args) {
        SpringApplication.run(CryptoAnalyzerApplication.class, args);
    }

}
