package fei.uv.mx.deliveryapp.Models;

import jakarta.persistence.*;

@Entity
@Table(name = "\"Address\"")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idAddress", nullable = false)
    private Integer id;

    @Column(name = "LineOne", length = 50)
    private String lineOne;

    @Column(name = "LineTwo", length = 50)
    private String lineTwo;

    @Column(name = "ZipCode", length = 10)
    private String zipCode;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idCity")
    private City idCity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idState")
    private State idState;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idUser")
    private User idUser;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idRestaurant")
    private Restaurant idRestaurant;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLineOne() {
        return lineOne;
    }

    public void setLineOne(String lineOne) {
        this.lineOne = lineOne;
    }

    public String getLineTwo() {
        return lineTwo;
    }

    public void setLineTwo(String lineTwo) {
        this.lineTwo = lineTwo;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public City getIdCity() {
        return idCity;
    }

    public void setIdCity(City idCity) {
        this.idCity = idCity;
    }

    public State getIdState() {
        return idState;
    }

    public void setIdState(State idState) {
        this.idState = idState;
    }

    public User getIdUser() {
        return idUser;
    }

    public void setIdUser(User idUser) {
        this.idUser = idUser;
    }

    public Restaurant getIdRestaurant() {
        return idRestaurant;
    }

    public void setIdRestaurant(Restaurant idRestaurant) {
        this.idRestaurant = idRestaurant;
    }

}