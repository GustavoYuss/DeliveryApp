package fei.uv.mx.deliveryapp.Seeders;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;

public class SeederConfig {

    @Bean
    CommandLineRunner runAllSeeders(RestaurantSeeder restaurantSeeder, RestaurantTypeSeeder restaurantTypeSeeder) {
        return args -> {
            restaurantSeeder.run();
            restaurantTypeSeeder.run();
        };
    }

}
