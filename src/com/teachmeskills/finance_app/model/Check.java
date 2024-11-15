package com.teachmeskills.finance_app.model;

public class Check {

    private double totalAmount;

    public Check(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public double getTotalAmount() {
        return totalAmount;
    }
}
