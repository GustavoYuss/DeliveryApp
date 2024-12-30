package fei.uv.mx.deliveryapp.Models;

import jakarta.persistence.*;

@Entity
@Table(name = "\"CustomerCart\"")
public class CustomerCart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idCustomerCart", nullable = false)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "idCustomer", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "idDish", nullable = false)
    private Dish dish;

    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    @Column(name = "costByProduct", nullable = false, length = 50)
    private String costByProduct;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Dish getDish() {
        return dish;
    }

    public void setDish(Dish dish) {
        this.dish = dish;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getCostByProduct() {
        return costByProduct;
    }

    public void setCostByProduct(String costByProduct) {
        this.costByProduct = costByProduct;
    }

}
