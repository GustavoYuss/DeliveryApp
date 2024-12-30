package fei.uv.mx.deliveryapp.Repositories;

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

    @Query("SELECT od FROM OrderRestaurant od WHERE od.idOrder.id = ?1")
    List<OrderRestaurant> findByOrderId(int orderId);

    @Query("SELECT od FROM OrderRestaurant od WHERE od.idDish.id = ?1")
    List<OrderRestaurant> findByDishId(int dishId);
}
