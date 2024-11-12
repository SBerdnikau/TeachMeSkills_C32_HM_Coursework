package com.teachmeskills.finance_app.model;

public class Order {
    //TODO описание класса ордера +
    private double orderTotal;

    public Order() {
    }

    public Order(double orderTotal) {
        this.orderTotal = orderTotal;
    }

    public double getOrderTotal() {
        return orderTotal;
    }

    public void setOrderTotal(double orderTotal) {
        this.orderTotal = orderTotal;
    }
}
