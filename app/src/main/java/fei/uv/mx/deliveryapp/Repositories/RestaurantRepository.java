package fei.uv.mx.deliveryapp.Repositories;

import fei.uv.mx.deliveryapp.Models.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RestaurantRepository extends JpaRepository<Restaurant, Integer> {
    default Restaurant createRestaurant(Restaurant restaurant) {
        return save(restaurant);
    }

    default Restaurant getRestaurant(int id) {
        return findById(id).orElse(null);
    }

    default boolean deleteRestaurant(int id) {
        if (existsById(id)) {
            deleteById(id);
            return true;
        }
        return false;
    }

    default boolean updateRestaurant(Restaurant restaurant) {
        if (existsById(restaurant.getId())) {
            save(restaurant);
            return true;
        }
        return false;
    }

    @Query("SELECT u FROM Restaurant u")
    List<Restaurant> getAllRestaurant();

    @Query("SELECT r FROM Restaurant r " +
            "JOIN RestaurantDishType rdt ON r.id = rdt.idRestaurant " +
            "JOIN DishType dt ON rdt.idDishType = dt.id " +
            "WHERE dt.id = :dishTypeId")
    List<Restaurant> findRestaurantsByDishTypeId(@Param("dishTypeId") Integer dishTypeId);

    List<Restaurant> findByNameRestaurantContainingIgnoreCase(String nameRestaurant);

}
