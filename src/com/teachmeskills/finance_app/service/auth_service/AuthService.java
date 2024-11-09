package com.teachmeskills.finance_app.service.auth_service;

import com.teachmeskills.finance_app.logs.LoggerService;
import com.teachmeskills.finance_app.resources.MockStorage;
import com.teachmeskills.finance_app.service.encrypt_service.EncryptorService;
import com.teachmeskills.finance_app.session.SessionManager;

import java.io.IOException;

public class AuthService {
    //TODO реализация авторизации пользователя +-
    public SessionManager auth(String login, String password) {

        MockStorage storageLikeDB = new MockStorage();
        LoggerService.logInfo("Получили данные пользователя");

        String loginFromDB = EncryptorService.decrypt(storageLikeDB.getLogin());
        String passwordFromDB = EncryptorService.decrypt(storageLikeDB.getPassword());

        LoggerService.logInfo("Начало проверки логина и пароля ");
        boolean result = login.toLowerCase().equals(loginFromDB) && password.equals(passwordFromDB);

        if(login.toLowerCase().equals(loginFromDB)  && password.equals(passwordFromDB)) {
                LoggerService.logInfo("Конец проверки логина и пароля: " + result);
                LoggerService.logInfo("Авторизация пользователя успешная");
                System.out.println("=======================================================");
                return new SessionManager();
        }else {
                LoggerService.logInfo("Конец проверки логина и пароля: " + result);
                LoggerService.logError("Авторизация пользователя не успешная");
                System.out.println("=======================================================");
                return null;
        }

    }
}
