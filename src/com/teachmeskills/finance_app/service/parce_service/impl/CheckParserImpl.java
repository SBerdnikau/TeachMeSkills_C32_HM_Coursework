package com.teachmeskills.finance_app.service.parce_service.impl;

import com.teachmeskills.finance_app.logs.LoggerService;
import com.teachmeskills.finance_app.service.parce_service.IParser;
import com.teachmeskills.finance_app.session.SessionManager;

import java.io.*;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CheckParserImpl implements IParser {


    @Override
    public void validatorDocument(SessionManager session) {
        //TODO Логика обработки документов. Сбор статистики по  чекам
        if (session.isSessionValid()){

            String directoryPath = "src/com/teachmeskills/finance_app/resources/data/checks";
            double totalAmount = 0.0;
            LoggerService.logInfo("Сессия валидная. Читаем файлы типа 2024_Electric_Bill_xx.txt");
            File directory = new File(directoryPath);
            if (directory.isDirectory()) {
                File[] files = directory.listFiles();
                if (files != null) {
                    for (File file : files) {
                        // Проверяем, соответствует ли имя файла формату (без учёта регистра)
                        if (file.getName().toLowerCase().matches("2024_electric_bill_\\d+\\.txt")) {
                            totalAmount += parsingDocument(file);
                        }
                    }
                }
            } else {
                System.out.println("Указанный путь не является папкой.");
            }
            System.out.println("========================================");
            System.out.printf("Общая сумма по чекам: %.2f%n", totalAmount);
            System.out.println("========================================");
        }else {
            LoggerService.logInfo("Сессия не валидная");
        }
    }

    @Override
    public double parsingDocument(File fileName) {
        double checkTotal = 0.0;
        LoggerService.logInfo("Парсим документ Check. Достаём поле общая стоимость...");
        Pattern pattern = Pattern.compile("Bill total amount EURO\\s*([0-9]*[.,][0-9]+)", Pattern.CASE_INSENSITIVE);

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Matcher matcher = pattern.matcher(line);
                if (matcher.find()) {
                    checkTotal = Double.parseDouble(matcher.group(1).replace(",","."));
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

        return checkTotal;
    }
}
