package fei.uv.mx.deliveryapp.Services.interfaces;

import fei.uv.mx.deliveryapp.Models.Restaurant;
import java.util.List;

public interface IRestaurantServices {
    public Restaurant createRestaurant(Restaurant restaurant);
    public Restaurant getRestaurant(int id);
    public boolean deleteRestaurant(int id);
    public boolean updateRestaurant(Restaurant restaurant);
    public List<Restaurant> getAllRestaurants();
}
