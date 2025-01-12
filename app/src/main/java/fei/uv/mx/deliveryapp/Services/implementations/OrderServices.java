package fei.uv.mx.deliveryapp.Services.implementations;

import fei.uv.mx.deliveryapp.Models.CustomerCart;
import fei.uv.mx.deliveryapp.Models.Order;
import fei.uv.mx.deliveryapp.Models.OrderAppRestaurant;
import fei.uv.mx.deliveryapp.Repositories.OrderAppRestaurantRepository;
import fei.uv.mx.deliveryapp.Repositories.OrderRepository;
import fei.uv.mx.deliveryapp.Services.interfaces.IOrderServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServices implements IOrderServices {
    @Autowired
    OrderRepository orderRepository;

    @Autowired
    OrderAppRestaurantRepository orderAppRestaurantRepository;

    @Override
    public Order createOrder(Order order) {
        return orderRepository.createOrder(order);
    }

    @Override
    public Order getOrder(int id) {
        return orderRepository.getOrder(id);
    }

    @Override
    public boolean deleteOrder(int id) {
        return orderRepository.deleteOrder(id);
    }

    @Override
    public boolean updateOrder(Order order) {
        return orderRepository.updateOrder(order);
    }

    @Override
    public List<Order> getOrdersByPaymentId(int paymentId) {
        return orderRepository.findByPaymentId(paymentId);
    }

    @Override
    public List<Order> getOrdersByUserId(int userId) {
        return orderRepository.findByUserId(userId);
    }

    @Override
    public OrderAppRestaurant createOrderAppRestaurant(OrderAppRestaurant orderAppRestaurant) {
        return orderAppRestaurantRepository.createOrderRestaurant(orderAppRestaurant);
    }

    @Override
    public List<CustomerCart> getCustomerCart(int idUser) {
        return orderRepository.getShoppingCarFromCustomer(idUser);
    }

    public Order getIdOrderGeneral(int idOrderRestaurant){
        return orderRepository.getIdOrderGeneral(idOrderRestaurant);
    }
}
