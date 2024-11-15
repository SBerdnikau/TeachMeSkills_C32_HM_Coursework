package com.teachmeskills.finance_app.statistic;

import com.teachmeskills.finance_app.logs.LoggerService;
import com.teachmeskills.finance_app.model.Check;
import com.teachmeskills.finance_app.model.Invoice;
import com.teachmeskills.finance_app.model.Order;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Statistic {

    private double totalCheckAmount;
    private double totalInvoiceAmount;
    private double totalOrderAmount;

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
        LoggerService.logInfo("Saving to file");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write(String.format("Total Check Amount: %.2f\n", totalCheckAmount));
            writer.write(String.format("Total Invoice Amount: %.2f\n",  totalInvoiceAmount));
            writer.write(String.format("Total Order Amount: %.2f\n", totalOrderAmount));
            writer.newLine();
            LoggerService.logInfo("Done");
        } catch (IOException e) {
            LoggerService.logError("Directory not found, file don't save");
            throw new RuntimeException("Exception IO, file don't save");
        }
    }

}
