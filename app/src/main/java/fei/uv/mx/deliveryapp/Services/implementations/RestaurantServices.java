package fei.uv.mx.deliveryapp.Services.implementations;

import fei.uv.mx.deliveryapp.Models.DishOrderDTO;
import fei.uv.mx.deliveryapp.Models.*;
import fei.uv.mx.deliveryapp.Repositories.OrderRestaurantRepository;
import fei.uv.mx.deliveryapp.Repositories.RestaurantDishTypeRepository;
import fei.uv.mx.deliveryapp.Repositories.RestaurantRepository;
import fei.uv.mx.deliveryapp.Services.interfaces.IRestaurantServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class RestaurantServices implements IRestaurantServices {
    @Autowired
    RestaurantRepository restaurantRepository;

    @Autowired
    RestaurantDishTypeRepository restaurantDishTypeRepository;

    @Autowired
    OrderRestaurantRepository orderRestaurantRepository;

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

    /**
     * @return
     */
    @Override
    public Restaurant getRestaurantByIdUser(int id) {
        return restaurantRepository.getRestaurantByUserId(id);
    }

    /**
     * @return
     */
    @Override
    public List<DishType> getDishesTypeByRestaurant(int idRestaurant) {
        return restaurantDishTypeRepository.getDishTypesFromRestaurant(idRestaurant);
    }

    /**
     * @return
     */
    @Override
    public List<OrderRestaurant> getOrderRestaurantByRestaurant(int idRestaurant) {
        return orderRestaurantRepository.findByOrderIdRestaurant(idRestaurant);
    }

    @Override
    public List<DishOrderDTO> getDishesFromOrderRestaurant(int idOrder) {
        return orderRestaurantRepository.getDishesFromOrderRestaurant(idOrder);
    }

    @Override
    public int getCountOrderByStatus(int idRestaurant, int idStatus) {
        return orderRestaurantRepository.getCountOfOrderByStatus(idRestaurant, idStatus);
    }

    @Override
    public int getCountOrderByStatusAndDate(int idRestaurant, int idStatus, LocalDate date) {
        return orderRestaurantRepository.getCountOfOrderByStatusAndDate(idRestaurant, idStatus, date);
    }

    public List<OrderRestaurant> getOrderRestaurantByDay(int idRestaurant, LocalDate startDate, LocalDate endDate) {
        return orderRestaurantRepository.findByOrderIdRestaurantAndDateRange(idRestaurant, startDate, endDate);
    }
}
