package fei.uv.mx.deliveryapp.Services.implementations;

import fei.uv.mx.deliveryapp.Models.Dish;
import fei.uv.mx.deliveryapp.Repositories.DishRepository;
import fei.uv.mx.deliveryapp.Services.interfaces.IDishServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest; // Correct import
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DishServices implements IDishServices {
    @Autowired
    DishRepository dishRepository;

    @Override
    public Dish createDish(Dish dish) {
        return dishRepository.createDish(dish);
    }

    @Override
    public Dish getDish(int id) {
        return dishRepository.getDish(id);
    }

    @Override
    public boolean deleteDish(int id) {
        return dishRepository.deleteDish(id);
    }

    @Override
    public boolean updateDish(Dish dish) {
        return dishRepository.updateDish(dish);
    }

    @Override
    public List<Dish> getDishesByDishTypeId(int dishTypeId) {
        return dishRepository.findByDishTypeId(dishTypeId);
    }

    @Override
    public List<Dish> getDishesByRestaurantId(int restaurantId) {
        return dishRepository.findByRestaurantId(restaurantId);
    }

    @Override
    public List<Dish> getTop5DishesByRestaurantId(int restaurantId, int excludedDishId) {
        return dishRepository.findTop5ByRestaurantIdExceptDishId(restaurantId,excludedDishId, PageRequest.of(0, 5));
    }
}
