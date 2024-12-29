package fei.uv.mx.deliveryapp.Models;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "\"Session\"")
public class Session {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idSession", nullable = false)
    private Integer id;

    @Column(name = "token")
    private String token;

    @Column(name = "loginDate")
    private LocalDateTime sessionDate;

    @Column(name = "ipAddress")
    private String ipAddress;

    @Column(name = "location")
    private String location;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idUser")
    private User idUser;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public LocalDateTime getSessionDate() {
        return sessionDate;
    }

    public void setSessionDate(LocalDateTime loginDate) {
        this.sessionDate = loginDate;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public User getUser() {
        return idUser;
    }

    public void setUser(User idUser) {
        this.idUser = idUser;
    }
}
