package fei.uv.mx.deliveryapp.Models;


import jakarta.persistence.*;

@Entity
@Table(name = "\"OrderRestaurantDish\"")
public class OrderRestaurantDish {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idOrderRestaurantDish", nullable = false)
    private Integer id;

    @Column(name = "amount")
    private int amount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idOrderRestaurant")
    private OrderRestaurant idOrderRestaurant;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idDish")
    private Dish idDish;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public OrderRestaurant getIdOrderRestaurant() {
        return idOrderRestaurant;
    }

    public void setIdOrderRestaurant(OrderRestaurant idOrderRestaurant) {
        this.idOrderRestaurant = idOrderRestaurant;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getAmount() {
        return amount;
    }

    public Dish getIdDish() {
        return idDish;
    }

    public void setIdDish(Dish idDish) {
        this.idDish = idDish;
    }
}
