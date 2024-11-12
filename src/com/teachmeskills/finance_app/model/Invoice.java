package com.teachmeskills.finance_app.model;

public class Invoice {
    //TODO описание класса инвойсы +
   private double totalAmount;

    public Invoice() {
    }

    public Invoice(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }
}
