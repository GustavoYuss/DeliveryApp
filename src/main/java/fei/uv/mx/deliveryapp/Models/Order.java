package fei.uv.mx.deliveryapp.Models;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "\"Order\"")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idOrder", nullable = false)
    private Integer id;

    @Column(name = "\"Date\"")
    private LocalDate date;

    @Column(name = "Total")
    private BigDecimal total;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "IdUser")
    private User idUser;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "IdPayment")
    private Payment idPayment;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public User getIdUser() {
        return idUser;
    }

    public void setIdUser(User idUser) {
        this.idUser = idUser;
    }

    public Payment getIdPayment() {
        return idPayment;
    }

    public void setIdPayment(Payment idPayment) {
        this.idPayment = idPayment;
    }
}