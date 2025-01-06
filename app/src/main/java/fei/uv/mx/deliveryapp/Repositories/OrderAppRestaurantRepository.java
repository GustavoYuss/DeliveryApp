package fei.uv.mx.deliveryapp.Repositories;

import fei.uv.mx.deliveryapp.Models.OrderAppRestaurant;
import fei.uv.mx.deliveryapp.Models.OrderRestaurant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderAppRestaurantRepository extends JpaRepository<OrderAppRestaurant, Integer> {
    default OrderAppRestaurant createOrderRestaurant(OrderAppRestaurant orderAppRestaurant) {
        return save(orderAppRestaurant);
    }
}
