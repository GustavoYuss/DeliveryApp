package fei.uv.mx.deliveryapp.Repositories;

import fei.uv.mx.deliveryapp.Models.DishType;
import fei.uv.mx.deliveryapp.Models.RestaurantDishType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface RestaurantDishTypeRepository extends JpaRepository<RestaurantDishType, Integer> {
    default RestaurantDishType createRestaurantDishType(RestaurantDishType restaurantDishType) {
        return save(restaurantDishType);
    }

    default RestaurantDishType getRestaurantDishType(int id) {
        return findById(id).orElse(null);
    }

    default boolean deleteRestaurantDishType(int id) {
        if (existsById(id)) {
            deleteById(id);
            return true;
        }
        return false;
    }

    default boolean updateRestaurantDishType(RestaurantDishType restaurantDishType) {
        if (existsById(restaurantDishType.getId())) {
            save(restaurantDishType);
            return true;
        }
        return false;
    }

    @Query("SELECT d FROM RestaurantDishType r JOIN r.idDishType d WHERE r.idRestaurant = ?1")
    List<DishType> getDishTypesFromRestaurant(int idRestaurant);
}
