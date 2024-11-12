package com.teachmeskills.finance_app.service.parsing.impl;

import com.teachmeskills.finance_app.logs.LoggerService;
import com.teachmeskills.finance_app.service.parsing.IParser;
import com.teachmeskills.finance_app.session.SessionManager;

import java.io.*;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InvoiceParserImpl implements IParser {

    @Override
    public void validatorDocument(String directoryPath, SessionManager session) {
        //TODO Логика обработки документов. Сбор статистики по инвойсам
        if (session.isSessionValid()){
            double totalAmount = 0.0;
            LoggerService.logInfo("Сессия валидная. Читаем файлы типа INVOICE_XX_2024_XX.txt");
            File directory = new File(directoryPath);
            if (directory.isDirectory()) {
                File[] files = directory.listFiles();
                if (files != null) {
                    for (File file : files) {
                        // Проверяем, соответствует ли имя файла формату (без учёта регистра)
                        if (file.getName().toLowerCase().matches( "invoice_\\d+_2024\\.txt|invoice_\\d+_2024_([a-z])\\.txt")) {
                            totalAmount += parsingDocument(file);
                        }
                    }
                }
            } else {
                System.out.println("Указанный путь не является папкой.");
            }
            System.out.println("========================================");
            System.out.printf("Общая сумма по инвойсам: %.2f%n", totalAmount);
            System.out.println("========================================");
        }else {
            LoggerService.logInfo("Сессия не валидная");
        }
    }

    @Override
    public double parsingDocument(File fileName) {
        double totalAmount = 0.0;
        LoggerService.logInfo("Парсим документ Invoice. Достаём поле общая стоимость...");
        Pattern pattern = Pattern.compile("Total amount\\s*(\\d+)\\$", Pattern.CASE_INSENSITIVE);

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Matcher matcher = pattern.matcher(line);
                if (matcher.find()) {
                    totalAmount = Double.parseDouble(matcher.group(1));
                    break; // Предположим, что мы хотим только первое вхождение
                }
            }
        } catch (FileNotFoundException e) {
            LoggerService.logError("Logger Error: Файл не был найден" + new Date());
            throw new RuntimeException(e);
        } catch (IOException e) {
            LoggerService.logError("Logger Error: Общая ошибка чтения/записи файла" + new Date());
            throw new RuntimeException(e);
        }

        return totalAmount;
    }
}
