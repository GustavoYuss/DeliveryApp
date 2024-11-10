package fei.uv.mx.deliveryapp.Repositories;

import fei.uv.mx.deliveryapp.Models.Dish;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DishRepository extends JpaRepository<Dish, Integer> {
    default Dish createDish(Dish dish) {
        return save(dish);
    }

    default Dish getDish(int id) {
        return findById(id).orElse(null);
    }

    default boolean deleteDish(int id) {
        if (existsById(id)) {
            deleteById(id);
            return true;
        }
        return false;
    }

    default boolean updateDish(Dish dish) {
        if (existsById(dish.getId())) {
            save(dish);
            return true;
        }
        return false;
    }

    @Query("SELECT d FROM Dish d WHERE d.idRestaurant.id = ?1")
    List<Dish> findByRestaurantId(int restaurantId);

    @Query("SELECT d FROM Dish d WHERE d.idDishType.id = ?1")
    List<Dish> findByDishTypeId(int dishTypeId);
}
