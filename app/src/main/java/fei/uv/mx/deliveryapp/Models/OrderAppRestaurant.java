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
    private OrderRestaurant idOrderRestaurant;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idRestaurant")
    private Restaurant idRestaurant;

    public Restaurant getIdRestaurant() {
        return this.idRestaurant;
    }

    public void setIdRestaurant(Restaurant idRestaurant) {
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

    public OrderRestaurant getIdOrderRestaurant() {
        return idOrderRestaurant;
    }

    public void setIdOrderRestaurant(OrderRestaurant idOrderRestaurant) {
        this.idOrderRestaurant = idOrderRestaurant;
    }
}
