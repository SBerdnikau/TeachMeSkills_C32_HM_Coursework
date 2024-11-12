package com.teachmeskills.finance_app.model;

public class Check {
    //TODO описание класса чеки +
    private double totalAmount;

    public Check() {
    }

    public Check(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }
}
