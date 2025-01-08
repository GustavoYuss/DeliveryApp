package fei.uv.mx.deliveryapp.Services.interfaces;

import fei.uv.mx.deliveryapp.Models.*;

import java.util.List;

public interface IRestaurantServices {
    Restaurant createRestaurant(Restaurant restaurant);
    Restaurant getRestaurant(int id);
    boolean deleteRestaurant(int id);
    boolean updateRestaurant(Restaurant restaurant);
    List<Restaurant> getAllRestaurants();
    Restaurant getRestaurantByIdUser(int id);
    List<DishType> getDishesTypeByRestaurant(int idRestaurant);
    List<OrderRestaurant> getOrderRestaurantByRestaurant(int idRestaurant);
    List<DishOrderDTO> getDishesFromOrderRestaurant(int idOrder);
}
