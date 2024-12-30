package fei.uv.mx.deliveryapp.Seeders;

import fei.uv.mx.deliveryapp.Models.RestaurantDishType;
import fei.uv.mx.deliveryapp.Repositories.RestaurantDishTypeRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class RestaurantDishTypeSeeder implements CommandLineRunner {

    private RestaurantDishTypeRepository restaurantDishTypeRepository;

    RestaurantDishTypeSeeder(RestaurantDishTypeRepository restaurantDishTypeRepository) {
        this.restaurantDishTypeRepository = restaurantDishTypeRepository;
    }

    /**
     * @param args
     * @throws Exception
     */
    @Override
    public void run(String... args) throws Exception {
        /*
        RestaurantDishType restaurantDishType = new RestaurantDishType();
        restaurantDishType.setIdRestaurant(1);
        restaurantDishType.setIdDishType(12);
        restaurantDishTypeRepository.createRestaurantDishType(restaurantDishType);

        RestaurantDishType restaurantDishType1 = new RestaurantDishType();
        restaurantDishType1.setIdRestaurant(1);
        restaurantDishType1.setIdDishType(15);
        restaurantDishTypeRepository.createRestaurantDishType(restaurantDishType1);

        RestaurantDishType restaurantDishType2 = new RestaurantDishType();
        restaurantDishType2.setIdRestaurant(2);
        restaurantDishType2.setIdDishType(10);
        restaurantDishTypeRepository.createRestaurantDishType(restaurantDishType2);

        RestaurantDishType restaurantDishType3 = new RestaurantDishType();
        restaurantDishType3.setIdRestaurant(2);
        restaurantDishType3.setIdDishType(9);
        restaurantDishTypeRepository.createRestaurantDishType(restaurantDishType3);

        RestaurantDishType restaurantDishType4 = new RestaurantDishType();
        restaurantDishType4.setIdRestaurant(2);
        restaurantDishType4.setIdDishType(20);
        restaurantDishTypeRepository.createRestaurantDishType(restaurantDishType4);

        RestaurantDishType restaurantDishType5 = new RestaurantDishType();
        restaurantDishType5.setIdRestaurant(4);
        restaurantDishType5.setIdDishType(15);
        restaurantDishTypeRepository.createRestaurantDishType(restaurantDishType5);

        RestaurantDishType restaurantDishType6 = new RestaurantDishType();
        restaurantDishType6.setIdRestaurant(6);
        restaurantDishType6.setIdDishType(15);
        restaurantDishTypeRepository.createRestaurantDishType(restaurantDishType6);

        RestaurantDishType restaurantDishType7 = new RestaurantDishType();
        restaurantDishType7.setIdRestaurant(6);
        restaurantDishType7.setIdDishType(15);
        restaurantDishTypeRepository.createRestaurantDishType(restaurantDishType7);

        RestaurantDishType restaurantDishType8 = new RestaurantDishType();
        restaurantDishType8.setIdRestaurant(4);
        restaurantDishType8.setIdDishType(12);
        restaurantDishTypeRepository.createRestaurantDishType(restaurantDishType8);

        RestaurantDishType restaurantDishType9 = new RestaurantDishType();
        restaurantDishType9.setIdRestaurant(4);
        restaurantDishType9.setIdDishType(15);
        restaurantDishTypeRepository.createRestaurantDishType(restaurantDishType9);

        RestaurantDishType restaurantDishType10 = new RestaurantDishType();
        restaurantDishType10.setIdRestaurant(5);
        restaurantDishType10.setIdDishType(12);
        restaurantDishTypeRepository.createRestaurantDishType(restaurantDishType10);

        RestaurantDishType restaurantDishType11 = new RestaurantDishType();
        restaurantDishType11.setIdRestaurant(6);
        restaurantDishType11.setIdDishType(2);
        restaurantDishTypeRepository.createRestaurantDishType(restaurantDishType11);

        RestaurantDishType restaurantDishType12 = new RestaurantDishType();
        restaurantDishType12.setIdRestaurant(6);
        restaurantDishType12.setIdDishType(6);
        restaurantDishTypeRepository.createRestaurantDishType(restaurantDishType12);

        RestaurantDishType restaurantDishType13 = new RestaurantDishType();
        restaurantDishType13.setIdRestaurant(6);
        restaurantDishType13.setIdDishType(11);
        restaurantDishTypeRepository.createRestaurantDishType(restaurantDishType13);
        */
    }
}
