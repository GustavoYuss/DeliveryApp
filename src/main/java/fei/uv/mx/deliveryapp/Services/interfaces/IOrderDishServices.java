package fei.uv.mx.deliveryapp.Services.interfaces;

import fei.uv.mx.deliveryapp.Models.OrderDish;
import java.util.List;

public interface IOrderDishServices {
    OrderDish createOrderDish(OrderDish orderDish);
    OrderDish getOrderDish(int id);
    boolean deleteOrderDish(int id);
    boolean updateOrderDish(OrderDish orderDish);
    List<OrderDish> getOrderDishesByOrderId(int orderId);
    List<OrderDish> getOrderDishesByDishId(int dishId);
}
