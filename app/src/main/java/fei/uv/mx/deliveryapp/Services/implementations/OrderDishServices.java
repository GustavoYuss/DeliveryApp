package fei.uv.mx.deliveryapp.Services.implementations;

import fei.uv.mx.deliveryapp.Models.OrderRestaurant;
import fei.uv.mx.deliveryapp.Models.OrderRestaurantDish;
import fei.uv.mx.deliveryapp.Repositories.OrderRestaurantDishRepository;
import fei.uv.mx.deliveryapp.Repositories.OrderRestaurantRepository;
import fei.uv.mx.deliveryapp.Services.interfaces.IOrderDishServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderDishServices implements IOrderDishServices {
    @Autowired
    OrderRestaurantRepository orderRestaurantRepository;

    @Autowired
    OrderRestaurantDishRepository orderRestaurantDishRepository;

    @Override
    public OrderRestaurant createOrderDish(OrderRestaurant orderRestaurant) {
        return orderRestaurantRepository.createOrderRestaurant(orderRestaurant);
    }

    @Override
    public OrderRestaurant getOrderDish(int id) {
        return orderRestaurantRepository.getOrderRestaurant(id);
    }

    @Override
    public boolean deleteOrderDish(int id) {
        return orderRestaurantRepository.deleteOrderDish(id);
    }

    @Override
    public boolean updateOrderDish(OrderRestaurant orderRestaurant) {
        return orderRestaurantRepository.updateOrderRestaurant(orderRestaurant);
    }

    @Override
    public OrderRestaurantDish saveDishesToOrder(OrderRestaurantDish orderRestaurantDish) {
        return orderRestaurantDishRepository.orderDishRestaurant(orderRestaurantDish);
    }


/*
    @Override
    public List<OrderRestaurant> getOrderDishesByOrderId(int orderId) {
        return orderRestaurantRepository.findByOrderId(orderId);
    }

    @Override
    public List<OrderRestaurant> getOrderDishesByDishId(int dishId) {
        return orderRestaurantRepository.findByDishId(dishId);
    }*/
}
