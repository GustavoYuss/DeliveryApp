package fei.uv.mx.deliveryapp.Services.implementations;

import fei.uv.mx.deliveryapp.Models.Restaurant;
import fei.uv.mx.deliveryapp.Repositories.RestaurantRepository;
import fei.uv.mx.deliveryapp.Repositories.UserRepository;
import fei.uv.mx.deliveryapp.Services.interfaces.IRestaurantServices;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class RestaurantServices implements IRestaurantServices {

    @Autowired
    RestaurantRepository restaurantRepository;

    /**
     * @param restaurant
     * @return
     */
    @Override
    public Restaurant createRestaurant(Restaurant restaurant) {
        return restaurantRepository.createRestaurant(restaurant);
    }

    /**
     * @param id
     * @return
     */
    @Override
    public Restaurant getRestaurant(int id) {
        return restaurantRepository.getRestaurant(id);
    }

    /**
     * @param id
     * @return
     */
    @Override
    public boolean deleteRestaurant(int id) {
        return restaurantRepository.deleteRestaurant(id);
    }

    /**
     * @param restaurant
     * @return
     */
    @Override
    public boolean updateRestaurant(Restaurant restaurant) {
        return restaurantRepository.updateRestaurant(restaurant);
    }

    /**
     * @return
     */
    @Override
    public List<Restaurant> getAllRestaurants() {
        return restaurantRepository.getAllRestaurant();
    }

}
