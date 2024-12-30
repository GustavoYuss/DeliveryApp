package fei.uv.mx.deliveryapp.DTOs;

import java.time.LocalDate;
import java.util.ArrayList;

public class OrderDTO {

    private LocalDate orderDate;

    private int total;

    private int idUser;

    private int idPayment;

    private ArrayList<OrderDishDTO> dishes;

    public LocalDate getOrderDate() {
        return this.orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public int getTotal() {
        return this.total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getIdUser() {
        return this.idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public int getIdPayment() {
        return this.idPayment;
    }

    public void setIdPayment(int idPayment) {
        this.idPayment = idPayment;
    }

    public ArrayList<OrderDishDTO> getDishes() {
        return this.dishes;
    }

    public void setDishes(ArrayList<OrderDishDTO> dishes) {
        this.dishes = dishes;
    }
}
