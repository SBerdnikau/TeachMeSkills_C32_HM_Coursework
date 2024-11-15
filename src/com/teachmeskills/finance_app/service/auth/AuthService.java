package com.teachmeskills.finance_app.service.auth;

import com.teachmeskills.finance_app.logs.LoggerService;
import com.teachmeskills.finance_app.resources.MockStorage;
import com.teachmeskills.finance_app.service.encrypt.EncryptorService;
import com.teachmeskills.finance_app.session.SessionManager;

public class AuthService {

    public SessionManager auth(String login, String password) {

        MockStorage storageLikeDB = new MockStorage();
        LoggerService.logInfo("Received user data");

        String loginFromDB = EncryptorService.decrypt(storageLikeDB.getLogin());
        String passwordFromDB = EncryptorService.decrypt(storageLikeDB.getPassword());

        LoggerService.logInfo("Start checking login and password");
        boolean result = login.toLowerCase().equals(loginFromDB) && password.equals(passwordFromDB);

        if(login.toLowerCase().equals(loginFromDB)  && password.equals(passwordFromDB)) {
                LoggerService.logInfo("End of login and password verification:" + result);
                LoggerService.logInfo("User authorization successful");
                System.out.println("=======================================================");
                return new SessionManager();
        }else {
                LoggerService.logInfo("End of login and password verification: " + result);
                LoggerService.logError("User authorization failed");
                System.out.println("=======================================================");
                return null;
        }

    }
}
