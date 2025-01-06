package fei.uv.mx.deliveryapp.Repositories;

import fei.uv.mx.deliveryapp.Models.Dish;
import fei.uv.mx.deliveryapp.Models.DishOrderDTO;
import fei.uv.mx.deliveryapp.Models.OrderAppRestaurant;
import fei.uv.mx.deliveryapp.Models.OrderRestaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderRestaurantRepository extends JpaRepository<OrderRestaurant, Integer> {
    default OrderRestaurant createOrderRestaurant(OrderRestaurant orderRestaurant) {
        return save(orderRestaurant);
    }

    default OrderRestaurant getOrderRestaurant(int id) {
        return findById(id).orElse(null);
    }

    default boolean deleteOrderDish(int id) {
        if (existsById(id)) {
            deleteById(id);
            return true;
        }
        return false;
    }

    default boolean updateOrderRestaurant(OrderRestaurant orderRestaurant) {
        if (existsById(orderRestaurant.getId())) {
            save(orderRestaurant);
            return true;
        }
        return false;
    }

    @Query("SELECT od.idOrderRestaurant.id FROM OrderAppRestaurant od WHERE od.idRestaurant = ?1")
    List<Integer> findByOrderId(int orderId);

    @Query("SELECT odr FROM OrderAppRestaurant oda JOIN oda.idOrderRestaurant odr WHERE oda.idRestaurant.id = ?1")
    List<OrderRestaurant> findByOrderIdRestaurant(int orderId);

    @Query("SELECT new fei.uv.mx.deliveryapp.Models.DishOrderDTO(od, ord.amount) FROM OrderRestaurantDish ord JOIN ord.idDish od WHERE ord.id = ?1")
    List<DishOrderDTO> getDishesFromOrderRestaurant(int orderId);

    /*
    @Query("SELECT od FROM OrderRestaurant od WHERE od.idDish.id = ?1")
    List<OrderRestaurant> findByDishId(int dishId);*/
}
