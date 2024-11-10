package fei.uv.mx.deliveryapp.Models;

import jakarta.persistence.*;

@Entity
@Table(name = "\"DishType\"")
public class DishType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idDishType", nullable = false)
    private Integer id;

    @Column(name = "DishType", length = 50)
    private String dishType;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDishType() {
        return dishType;
    }

    public void setDishType(String dishType) {
        this.dishType = dishType;
    }

}