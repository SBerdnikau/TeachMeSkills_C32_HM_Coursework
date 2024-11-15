package com.teachmeskills.finance_app.statistic;

import com.teachmeskills.finance_app.logs.LoggerService;
import com.teachmeskills.finance_app.model.Check;
import com.teachmeskills.finance_app.model.Invoice;
import com.teachmeskills.finance_app.model.Order;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Statistic {

    private double totalCheckAmount = 0;
    private double totalInvoiceAmount = 0;
    private double totalOrderAmount = 0;

    public void addCheck(Check check) {
        totalCheckAmount += check.getTotalAmount();
    }

    public void addInvoice(Invoice invoice) {
        totalInvoiceAmount += invoice.getTotalAmount();
    }

    public void addOrder(Order order) {
        totalOrderAmount += order.getTotalAmount();
    }

    public void printStatistics() {
        System.out.println("====================STATISTIC===========================");
        System.out.printf("Total Check Amount: %.2f\n", totalCheckAmount);
        System.out.printf("Total Invoice Amount: %.2f\n",  totalInvoiceAmount);
        System.out.printf("Total Order Amount: %.2f\n", totalOrderAmount);
        System.out.println("=======================================================");
    }

    public void saveStatistics(String fileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write("Total Check Amount: " + totalCheckAmount + "\n");
            writer.write("Total Invoice Amount: " + totalInvoiceAmount + "\n");
            writer.write("Total Order Amount: " + totalOrderAmount + "\n");
        } catch (IOException e) {
            LoggerService.logError("Logger-error: Directory not found, file don't save");
            throw new RuntimeException("Exception IO, file don't save");
        }
    }

}
