package fei.uv.mx.deliveryapp.Services.implementations;

import fei.uv.mx.deliveryapp.Models.OrderDish;
import fei.uv.mx.deliveryapp.Repositories.OrderDishRepository;
import fei.uv.mx.deliveryapp.Services.interfaces.IOrderDishServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderDishServices implements IOrderDishServices {
    @Autowired
    OrderDishRepository orderDishRepository;

    @Override
    public OrderDish createOrderDish(OrderDish orderDish) {
        return orderDishRepository.createOrderDish(orderDish);
    }

    @Override
    public OrderDish getOrderDish(int id) {
        return orderDishRepository.getOrderDish(id);
    }

    @Override
    public boolean deleteOrderDish(int id) {
        return orderDishRepository.deleteOrderDish(id);
    }

    @Override
    public boolean updateOrderDish(OrderDish orderDish) {
        return orderDishRepository.updateOrderDish(orderDish);
    }

    @Override
    public List<OrderDish> getOrderDishesByOrderId(int orderId) {
        return orderDishRepository.findByOrderId(orderId);
    }

    @Override
    public List<OrderDish> getOrderDishesByDishId(int dishId) {
        return orderDishRepository.findByDishId(dishId);
    }
}
