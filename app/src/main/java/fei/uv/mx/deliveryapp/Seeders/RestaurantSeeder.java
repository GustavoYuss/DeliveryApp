package fei.uv.mx.deliveryapp.Seeders;

import fei.uv.mx.deliveryapp.Models.Restaurant;
import fei.uv.mx.deliveryapp.Repositories.RestaurantRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.time.LocalTime;

@Order(3)
@Component
public class RestaurantSeeder implements CommandLineRunner {

    private RestaurantRepository restaurantRepository;

    public RestaurantSeeder(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    /**
     * @param args
     * @throws Exception
     */
    @Override
    public void run(String... args) throws Exception {


        Restaurant restaurant1 = new Restaurant();
        restaurant1.setId(1);
        restaurant1.setNameRestaurant("Starbucks");
        restaurant1.setOpenTime(LocalTime.of(9, 0));
        restaurant1.setCloseTime(LocalTime.of(22, 0));
        restaurant1.setImagePath("/images/Starbucks.jpg");
        restaurant1.setImageLogoPath("/images/Startbucks-Logo.png");

        Restaurant restaurant2 = new Restaurant();
        restaurant2.setId(2);
        restaurant2.setNameRestaurant("Freshi");
        restaurant2.setOpenTime(LocalTime.of(10, 0));
        restaurant2.setCloseTime(LocalTime.of(23, 0));
        restaurant2.setImagePath("/images/Freshi.jpg");
        restaurant2.setImageLogoPath("/images/Freshi-Logo.png");

        Restaurant restaurant3 = new Restaurant();
        restaurant3.setId(3);
        restaurant3.setNameRestaurant("McDonalds");
        restaurant3.setOpenTime(LocalTime.of(8, 0));
        restaurant3.setCloseTime(LocalTime.of(20, 0));
        restaurant3.setImagePath("/images/macdonals.jpeg");
        restaurant3.setImageLogoPath("/images/McDonalds-Logo.jpg");

        Restaurant restaurant4 = new Restaurant();
        restaurant4.setId(2);
        restaurant4.setNameRestaurant("Bola de Oro");
        restaurant4.setOpenTime(LocalTime.of(9, 0));
        restaurant4.setCloseTime(LocalTime.of(22, 0));
        restaurant4.setImagePath("/images/BolaDeOro.jpeg");
        restaurant4.setImageLogoPath("/images/BolaOro-Logo.png");

        Restaurant restaurant5 = new Restaurant();
        restaurant5.setId(5);
        restaurant5.setNameRestaurant("Bubba");
        restaurant5.setOpenTime(LocalTime.of(10, 0));
        restaurant5.setCloseTime(LocalTime.of(23, 0));
        restaurant5.setImagePath("/images/Bubba.jpeg");
        restaurant5.setImageLogoPath("/images/Bubba-Logo.jpg");

        Restaurant restaurant6 = new Restaurant();
        restaurant2.setId(6);
        restaurant6.setNameRestaurant("Little Caesars");
        restaurant6.setOpenTime(LocalTime.of(8, 0));
        restaurant6.setCloseTime(LocalTime.of(20, 0));
        restaurant6.setImagePath("/images/LittleCaesars.jpg");
        restaurant6.setImageLogoPath("/images/Ceasears_Logo.jpg");

        restaurantRepository.save(restaurant1);
        restaurantRepository.save(restaurant2);
        restaurantRepository.save(restaurant3);
        restaurantRepository.save(restaurant4);
        restaurantRepository.save(restaurant5);
        restaurantRepository.save(restaurant6);
    }
}
