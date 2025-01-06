package fei.uv.mx.deliveryapp.Models;

import jakarta.persistence.*;

@Entity
@Table(name = "\"RestaurantDishType\"")
public class RestaurantDishType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idRestaurantDishType", nullable = false)
    private Integer id;

    @Column(name = "idRestaurant", nullable = false)
    private Integer idRestaurant;

    @ManyToOne
    @JoinColumn(name = "idDishType")
    private DishType idDishType;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdRestaurant() {
        return idRestaurant;
    }

    public void setIdRestaurant(Integer idRestaurant) {
        this.idRestaurant = idRestaurant;
    }

    public DishType getIdDishType() {
        return idDishType;
    }

    public void setIdDishType(DishType idDishType) {
        this.idDishType = idDishType;
    }

}