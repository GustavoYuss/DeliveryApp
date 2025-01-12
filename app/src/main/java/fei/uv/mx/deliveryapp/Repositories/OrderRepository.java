package fei.uv.mx.deliveryapp.Repositories;

import fei.uv.mx.deliveryapp.Models.CustomerCart;
import fei.uv.mx.deliveryapp.Models.Order;
import fei.uv.mx.deliveryapp.Models.OrderRestaurantDish;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
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

    List<Order> findByDateBetween(LocalDate startDate, LocalDate endDate);

    @Query("SELECT o FROM Order o WHERE o.idUser.id = ?1")
    List<Order> findByUserId(int userId);

    @Query("SELECT o FROM Order o WHERE o.idPayment.id = ?1")
    List<Order> findByPaymentId(int paymentId);

    @Query("SELECT cc FROM CustomerCart cc WHERE cc.user.id = ?1")
    List<CustomerCart> getShoppingCarFromCustomer(int customerId);

    @Query("SELECT o FROM Order o WHERE " +
            "(:startDate IS NULL OR o.date >= :startDate) AND " +
            "(:endDate IS NULL OR o.date <= :endDate) AND " +
            "(:status IS NULL OR o.status = :status)")
    List<Order> findFilteredOrders(@Param("startDate") LocalDate startDate,
                                   @Param("endDate") LocalDate endDate,
                                   @Param("status") String status);

    @Query("SELECT ora.idOrder FROM OrderAppRestaurant ora WHERE ora.idOrderRestaurant.id = ?1")
    Order getIdOrderGeneral(int idOrderRestaurant);

    @Modifying
    @Transactional // Garantiza que ambas consultas se ejecuten dentro de la misma transacción
    @Query("""
        UPDATE OrderRestaurant orr SET orr.idStatus.id = :newStatusId 
        WHERE orr.id IN (
            SELECT oar.idOrderRestaurant.id 
            FROM OrderAppRestaurant oar 
            WHERE oar.idOrder.id = :orderId
        )
    """)
    void updateOrderRestaurantStatusByOrderId(@Param("newStatusId") Integer newStatusId,
                                              @Param("orderId") Integer orderId);

    @Modifying
    @Transactional
    @Query("""
        UPDATE Order o SET o.status.id = :newStatusId 
        WHERE o.id = :orderId
    """)
    void updateOrderStatus(@Param("newStatusId") Integer newStatusId,
                           @Param("orderId") Integer orderId);
}

