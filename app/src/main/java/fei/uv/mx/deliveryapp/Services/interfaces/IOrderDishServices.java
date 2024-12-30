package fei.uv.mx.deliveryapp.Services.interfaces;

import fei.uv.mx.deliveryapp.Models.OrderRestaurant;
import java.util.List;

public interface IOrderDishServices {
    OrderRestaurant createOrderDish(OrderRestaurant orderRestaurant);
    OrderRestaurant getOrderDish(int id);
    boolean deleteOrderDish(int id);
    boolean updateOrderDish(OrderRestaurant orderRestaurant);
    List<OrderRestaurant> getOrderDishesByOrderId(int orderId);
    List<OrderRestaurant> getOrderDishesByDishId(int dishId);
}
