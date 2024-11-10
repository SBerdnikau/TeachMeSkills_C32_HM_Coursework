package com.teachmeskills.finance_app;

import com.teachmeskills.finance_app.logs.LoggerService;
import com.teachmeskills.finance_app.service.auth_service.AuthService;
import com.teachmeskills.finance_app.service.parce_service.impl.OrderParserDoc;
import com.teachmeskills.finance_app.session.SessionManager;

public class ApplicationRunner {
    public static void main(String[] args)  {
        //TODO Логика инициализации, аутентификации, обработки документов и загрузки статистики
        LoggerService.logInfo("Запуск приложения.");
        AuthService authService = new AuthService();
        SessionManager sessionClient1 = authService.auth("admin", "TMC32Java");
        OrderParserDoc order = new OrderParserDoc();
        order.validatorOrder(sessionClient1);
    }

}