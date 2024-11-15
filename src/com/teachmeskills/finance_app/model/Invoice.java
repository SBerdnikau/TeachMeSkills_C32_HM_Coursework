package com.teachmeskills.finance_app.model;

public class Invoice {

    private double totalAmount;

    public Invoice(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public double getTotalAmount() {
        return totalAmount;
    }
}
