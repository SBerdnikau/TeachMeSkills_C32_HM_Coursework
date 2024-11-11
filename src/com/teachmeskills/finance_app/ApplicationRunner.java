package com.teachmeskills.finance_app;

import com.teachmeskills.finance_app.logs.LoggerService;
import com.teachmeskills.finance_app.service.auth_service.AuthService;
import com.teachmeskills.finance_app.service.parce_service.impl.CheckParserImpl;
import com.teachmeskills.finance_app.service.parce_service.impl.InvoiceParserImpl;
import com.teachmeskills.finance_app.service.parce_service.impl.OrderParserImpl;
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
        LoggerService.logInfo("Запуск приложения.");
        AuthService authService = new AuthService();
        SessionManager sessionClient1 = authService.auth(inputLogin, inputPass);
        OrderParserImpl order = new OrderParserImpl();
        CheckParserImpl check = new CheckParserImpl();
        InvoiceParserImpl invoice = new InvoiceParserImpl();
        order.validatorDocument(sessionClient1);
        check.validatorDocument(sessionClient1);
        invoice.validatorDocument(sessionClient1);
    }

}