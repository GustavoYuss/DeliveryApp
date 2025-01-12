package fei.uv.mx.deliveryapp.DTOs;

import fei.uv.mx.deliveryapp.Models.Payment;

public class OrderRequestDTO {
    private Integer userID;
    private String address;
    private Payment payment;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }
}
