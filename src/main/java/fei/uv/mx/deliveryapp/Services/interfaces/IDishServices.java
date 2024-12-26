package fei.uv.mx.deliveryapp.Services.interfaces;

import fei.uv.mx.deliveryapp.Models.Dish;
import java.util.List;

public interface IDishServices {
    Dish createDish(Dish dish);
    Dish getDish(int id);
    boolean deleteDish(int id);
    boolean updateDish(Dish dish);
    List<Dish> getDishesByRestaurantId(int restaurantId);
    List<Dish> getTop5DishesByRestaurantId(int restaurantId, int excludedDishId);
    List<Dish> getDishesByDishTypeId(int restaurantId);
}
