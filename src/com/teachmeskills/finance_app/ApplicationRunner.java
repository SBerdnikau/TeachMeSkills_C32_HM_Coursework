package com.teachmeskills.finance_app;

import com.teachmeskills.finance_app.logs.LoggerService;
import com.teachmeskills.finance_app.service.auth.AuthService;
import com.teachmeskills.finance_app.service.parsing.Parser;
import com.teachmeskills.finance_app.session.SessionManager;

import java.util.Scanner;

public class ApplicationRunner {
    public static void main(String[] args)  {
        System.out.print("Insert login: ");//admin
        Scanner scanner = new Scanner(System.in);
        String inputLogin = scanner.nextLine();
        System.out.print("Insert password: ");//TMC32Java
        String inputPass = scanner.nextLine();
        AuthService authService = new AuthService();
        SessionManager sessionClient1 = authService.auth(inputLogin, inputPass);
        if (sessionClient1.isSessionValid()){
            System.out.print("Insert path to directory: ");
            String directoryPath = scanner.nextLine();// src/com/teachmeskills/finance_app/resources/data
            Parser parser = new Parser();
            parser.validationFile(directoryPath, sessionClient1);
        }else {
            LoggerService.logInfo("Session is not valid");
        }
    }
}