package fei.uv.mx.deliveryapp.Repositories;

import fei.uv.mx.deliveryapp.Models.OrderRestaurantDish;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRestaurantDishRepository extends JpaRepository<OrderRestaurantDish, Integer> {
    default OrderRestaurantDish orderDishRestaurant(OrderRestaurantDish orderRestaurantDish) {
        return save(orderRestaurantDish);
    }
}
