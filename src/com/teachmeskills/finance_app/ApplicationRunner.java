package com.teachmeskills.finance_app;

import com.teachmeskills.finance_app.logs.LoggerService;
import com.teachmeskills.finance_app.service.auth.AuthService;
import com.teachmeskills.finance_app.service.parsing.impl.CheckParserImpl;
import com.teachmeskills.finance_app.service.parsing.impl.InvoiceParserImpl;
import com.teachmeskills.finance_app.service.parsing.impl.OrderParserImpl;
import com.teachmeskills.finance_app.session.SessionManager;

import java.util.Scanner;

public class ApplicationRunner {
    public static void main(String[] args)  {
        //TODO Логика инициализации, аутентификации, обработки документов и загрузки статистики
        System.out.print("Введите логин: ");//admin
        Scanner scanner = new Scanner(System.in);
        String inputLogin = scanner.nextLine();
        System.out.print("Введите пароль: ");//TMC32Java
        String inputPass = scanner.nextLine();
        AuthService authService = new AuthService();
        SessionManager sessionClient1 = authService.auth(inputLogin, inputPass);
        if (sessionClient1.isSessionValid()){
            OrderParserImpl order = new OrderParserImpl();
            CheckParserImpl check = new CheckParserImpl();
            InvoiceParserImpl invoice = new InvoiceParserImpl();
            System.out.print("Введите путь к файлу Ордеров: ");
            String directoryPathOrder = scanner.nextLine();// src/com/teachmeskills/finance_app/resources/data/orders
            System.out.print("Введите путь к файлу Чеков: ");
            String directoryPathCheck = scanner.nextLine();//src/com/teachmeskills/finance_app/resources/data/checks
            System.out.print("Введите путь к файлу Инвойсов: ");
            String directoryPathInvoice = scanner.nextLine();  //src/com/teachmeskills/finance_app/resources/data/invoices
            order.validatorDocument(directoryPathOrder ,sessionClient1);
            check.validatorDocument(directoryPathCheck, sessionClient1);
            invoice.validatorDocument(directoryPathInvoice, sessionClient1);
        }else {
            LoggerService.logInfo("Сессия не валидная");
        }

    }

}