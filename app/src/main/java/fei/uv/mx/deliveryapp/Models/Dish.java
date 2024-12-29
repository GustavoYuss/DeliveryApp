package fei.uv.mx.deliveryapp.Models;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "\"Dish\"")
public class Dish {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idDish", nullable = false)
    private Integer id;

    @Column(name = "Name", length = 50)
    private String name;

    @Lob
    @Column(name = "Description")
    private String description;

    @Column(name = "NormalPrice")
    private BigDecimal normalPrice;

    @Column(name = "OfferPrice")
    private BigDecimal offerPrice;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "IdRestaurant")
    private Restaurant idRestaurant;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "IdDishType")
    private Dish idDishType;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getNormalPrice() {
        return normalPrice;
    }

    public void setNormalPrice(BigDecimal normalPrice) {
        this.normalPrice = normalPrice;
    }

    public BigDecimal getOfferPrice() {
        return offerPrice;
    }

    public void setOfferPrice(BigDecimal offerPrice) {
        this.offerPrice = offerPrice;
    }

    public Restaurant getIdRestaurant() {
        return idRestaurant;
    }

    public void setIdRestaurant(Restaurant idRestaurant) {
        this.idRestaurant = idRestaurant;
    }

    public Dish getIdDishType() {
        return idDishType;
    }

    public void setIdDishType(Dish idDishType) {
        this.idDishType = idDishType;
    }
}