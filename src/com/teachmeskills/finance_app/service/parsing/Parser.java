package com.teachmeskills.finance_app.service.parsing;

import com.teachmeskills.finance_app.logs.LoggerService;
import com.teachmeskills.finance_app.model.Check;
import com.teachmeskills.finance_app.model.Invoice;
import com.teachmeskills.finance_app.model.Order;
import com.teachmeskills.finance_app.session.SessionManager;
import com.teachmeskills.finance_app.statistic.Statistic;
import com.teachmeskills.finance_app.utils.Constants;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {

    public void validationFile(String pathDirectory, SessionManager session){

        if (session.isSessionValid()){

            LoggerService.logInfo("Validation path to directory");

            File rootDir = new File(pathDirectory);
            if (!rootDir.exists() || !rootDir.isDirectory()) {
                System.out.println("Directory does not exist.");
                return;
            }

            Statistic statistics = new Statistic();

            try {
                Files.walk(rootDir.toPath())
                        .filter(Files::isRegularFile)
                        .filter(path -> path.getFileName().toString().contains("2024")
                                && path.getFileName().toString().endsWith(".txt"))
                        .forEach(path -> parserFile(path.toFile(), statistics));
            } catch (IOException e) {
                LoggerService.logError("Logger-error: the file is not readable");
                throw new RuntimeException("Exception IO");
            }

            statistics.printStatistics();
            statistics.saveStatistics(Constants.STATS_FILE_NAME);

        }else {
            LoggerService.logInfo("Session is not valid");
        }

    }

    private static void parserFile(File file, Statistic statistics) {
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            Pattern checkPattern = Pattern.compile(Constants.CHECK_REGEX);
            Pattern invoicePattern = Pattern.compile(Constants.INVOICE_REGEX);
            Pattern orderPattern = Pattern.compile(Constants.ORDER_REGEX);
            while ((line = reader.readLine()) != null) {
                Matcher checkMatcher = checkPattern.matcher(line);
                if (checkMatcher.find()) {
                    statistics.addCheck(new Check(Double.parseDouble(checkMatcher.group(1).replace(",", "."))));
                }

                Matcher invoiceMatcher = invoicePattern.matcher(line);
                if (invoiceMatcher.find()) {
                    statistics.addInvoice(new Invoice(Double.parseDouble(invoiceMatcher.group(1))));
                }

                Matcher orderMatcher = orderPattern.matcher(line);
                if (orderMatcher.find()) {
                    statistics.addOrder(new Order(Double.parseDouble(orderMatcher.group(1).replace(",", ""))));
                }
            }
        } catch (IOException e) {
            LoggerService.logError("Logger-error: the file is not readable");
            throw new RuntimeException("Exception IO");
        }
    }

}
