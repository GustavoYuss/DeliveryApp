package fei.uv.mx.deliveryapp.Models;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "\"OrderRestaurant\"")
public class OrderRestaurant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idOrderRestaurant", nullable = false)
    private Integer id;

    @Column(name = "UnitPrice")
    private BigDecimal unitPrice;

    @Column(name = "Amount")
    private Integer amount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idOrder")
    private Order idOrder;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idDish")
    private Dish idDish;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idRestaurant")
    private Restaurant idRestaurant;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Order getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(Order idOrder) {
        this.idOrder = idOrder;
    }

    public Dish getIdDish() {
        return idDish;
    }

    public void setIdDish(Dish idDish) {
        this.idDish = idDish;
    }

    public Restaurant getIdRestaurant() {return this.idRestaurant;}

    public void setIdRestaurant(Restaurant idRestaurant) {
        this.idRestaurant = idRestaurant;
    }

}