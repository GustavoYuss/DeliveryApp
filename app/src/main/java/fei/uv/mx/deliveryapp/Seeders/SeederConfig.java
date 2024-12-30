package fei.uv.mx.deliveryapp.Seeders;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SeederConfig {

    @Bean
    CommandLineRunner runAllSeeders() {
        return args -> {
        };
    }

}
