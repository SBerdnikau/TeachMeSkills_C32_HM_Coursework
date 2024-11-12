package com.teachmeskills.finance_app.service.parsing.impl;

import com.teachmeskills.finance_app.logs.LoggerService;
import com.teachmeskills.finance_app.model.Order;
import com.teachmeskills.finance_app.service.parsing.IParser;
import com.teachmeskills.finance_app.session.SessionManager;

import java.io.*;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class OrderParserImpl implements IParser {

    public void validatorDocument(String directoryPath, SessionManager session){
        if (session.isSessionValid()){
            double totalOrder = 0.0;
            LoggerService.logInfo("Сессия валидная. Читаем файлы типа 2024_order_xxx.txt");
            File directory = new File(directoryPath);
            if (directory.isDirectory()) {
                File[] files = directory.listFiles();
                if (files != null) {
                    for (File file : files) {
                        // Проверяем, соответствует ли имя файла формату (без учёта регистра)
                        if (file.getName().toLowerCase().matches("2024_order_\\d+\\.txt")) {
                            totalOrder += parsingDocument(file);
                        }
                    }
                }
            } else {
                System.out.println("Указанный путь не является папкой.");
            }
            System.out.println("========================================");
            System.out.printf("Общая сумма по ордерам: %.2f%n", totalOrder);
            System.out.println("========================================");
        }else {
            LoggerService.logInfo("Сессия не валидная");
        }

    }

    public double parsingDocument(File fileName) {
        //TODO Логика обработки документов. Сбор статистики по  ордерам
        LoggerService.logInfo("Парсим документ Order. Достаём поле общая стоимость...");
        Pattern pattern = Pattern.compile("Order Total\\s*(\\d{1,3})(,\\d{3})*\\.(\\d{2})", Pattern.CASE_INSENSITIVE);
        Order order = new Order();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Matcher matcher = pattern.matcher(line);
                if (matcher.find()) {
                    order.setOrderTotal( Double.parseDouble(matcher.group(1)));
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

        return order.getOrderTotal();
    }

}