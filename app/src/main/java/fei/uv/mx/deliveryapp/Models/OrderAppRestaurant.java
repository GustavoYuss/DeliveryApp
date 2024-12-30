package fei.uv.mx.deliveryapp.Models;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "\"OrderAppRestaurant\"")
public class OrderAppRestaurant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idOrderAAppRestaurant", nullable = false)
    private int idOrderAAppRestaurant;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idOrder")
    private Order idOrder;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idOrderRestaurant")
    private OrderRestaurant idRestaurant;


    public OrderRestaurant getIdRestaurant() {
        return this.idRestaurant;
    }

    public void setIdRestaurant(OrderRestaurant idRestaurant) {
        this.idRestaurant = idRestaurant;
    }

    public Order getIdOrder() {
        return this.idOrder;
    }

    public void setIdOrder(Order idOrder) {
        this.idOrder = idOrder;
    }

    public int getIdOrderAAppRestaurant() {
        return this.idOrderAAppRestaurant;
    }

    public void setIdOrderAAppRestaurant(int idOrderAAppRestaurant) {
        this.idOrderAAppRestaurant = idOrderAAppRestaurant;
    }
}
