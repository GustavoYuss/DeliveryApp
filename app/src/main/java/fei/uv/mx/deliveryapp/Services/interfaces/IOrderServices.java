package fei.uv.mx.deliveryapp.Services.interfaces;

import fei.uv.mx.deliveryapp.Models.Order;
import java.util.List;

public interface IOrderServices {
    Order createOrder(Order order);
    Order getOrder(int id);
    boolean deleteOrder(int id);
    boolean updateOrder(Order order);
    List<Order> getOrdersByPaymentId(int paymentId);
    List<Order> getOrdersByUserId(int userId);
}
