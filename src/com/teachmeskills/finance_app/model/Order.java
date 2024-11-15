package com.teachmeskills.finance_app.model;

public class Order {

    private double totalAmount;

    public Order(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public double getTotalAmount() {
        return totalAmount;
    }
}
