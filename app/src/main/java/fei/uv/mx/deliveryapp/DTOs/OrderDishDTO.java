package fei.uv.mx.deliveryapp.DTOs;

public class OrderDishDTO {
    private int dishID;
    private int amount;
    private int price;

    public int getDishID() {
        return this.dishID;
    }

    public void setDishID(int dishID) {
        this.dishID = dishID;
    }

    public int getAmount() {
        return this.amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getPrice() {
        return this.price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
