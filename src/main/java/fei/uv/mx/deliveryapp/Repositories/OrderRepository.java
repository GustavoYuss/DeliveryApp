package fei.uv.mx.deliveryapp.Repositories;

import fei.uv.mx.deliveryapp.Models.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Integer> {
    default Order createOrder(Order order) {
        return save(order);
    }

    default Order getOrder(int id) {
        return findById(id).orElse(null);
    }

    default boolean deleteOrder(int id) {
        if (existsById(id)) {
            deleteById(id);
            return true;
        }
        return false;
    }

    default boolean updateOrder(Order order) {
        if (existsById(order.getId())) {
            save(order);
            return true;
        }
        return false;
    }

    @Query("SELECT o FROM Order o WHERE o.idUser.id = ?1")
    List<Order> findByUserId(int userId);

    @Query("SELECT o FROM Order o WHERE o.idPayment.id = ?1")
    List<Order> findByPaymentId(int paymentId);
}
