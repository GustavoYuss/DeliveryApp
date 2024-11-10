package fei.uv.mx.deliveryapp.Repositories;

import fei.uv.mx.deliveryapp.Models.OrderDish;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderDishRepository extends JpaRepository<OrderDish, Integer> {
    default OrderDish createOrderDish(OrderDish orderDish) {
        return save(orderDish);
    }

    default OrderDish getOrderDish(int id) {
        return findById(id).orElse(null);
    }

    default boolean deleteOrderDish(int id) {
        if (existsById(id)) {
            deleteById(id);
            return true;
        }
        return false;
    }

    default boolean updateOrderDish(OrderDish orderDish) {
        if (existsById(orderDish.getId())) {
            save(orderDish);
            return true;
        }
        return false;
    }

    @Query("SELECT od FROM OrderDish od WHERE od.idOrder.id = ?1")
    List<OrderDish> findByOrderId(int orderId);

    @Query("SELECT od FROM OrderDish od WHERE od.idDish.id = ?1")
    List<OrderDish> findByDishId(int dishId);
}
