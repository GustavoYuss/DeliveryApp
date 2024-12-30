package fei.uv.mx.deliveryapp.Repositories;

import fei.uv.mx.deliveryapp.Models.DishType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DishTypeRepository extends JpaRepository<DishType, Integer>{
    default DishType createDishType(DishType dishType) {
        return save(dishType);
    }

    default DishType getDishType(int id) {
        return findById(id).orElse(null);
    }

    default boolean deleteDishType(int id) {
        if (existsById(id)) {
            deleteById(id);
            return true;
        }
        return false;
    }

    default boolean updateDishType(DishType dishType) {
        if (existsById(dishType.getId())) {
            save(dishType);
            return true;
        }
        return false;
    }

}
