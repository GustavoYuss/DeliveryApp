package fei.uv.mx.deliveryapp.Models;

import jakarta.persistence.*;

@Entity
@Table(name = "\"RestaurantDishType\"")
public class RestaurantDishType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idRestaurantDishType", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idRestaurant")
    private Restaurant idRestaurant;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Restaurant getIdRestaurant() {
        return idRestaurant;
    }

    public void setIdRestaurant(Restaurant idRestaurant) {
        this.idRestaurant = idRestaurant;
    }

}