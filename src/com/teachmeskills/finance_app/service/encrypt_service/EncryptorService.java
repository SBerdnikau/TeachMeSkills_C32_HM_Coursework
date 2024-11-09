package com.teachmeskills.finance_app.service.encrypt_service;

import com.teachmeskills.finance_app.logs.LoggerService;

import java.util.Base64;

public class EncryptorService {

    //TODO Реализовать шифрование и расшифрование с солью +-
    public static String encrypt(String inputData){
        return Base64.getEncoder().encodeToString(inputData.getBytes());
    }

    public static String decrypt(String inputData){
        LoggerService.logInfo("Идёт дешифрование входных данных пользователя...");
        byte[] bytes = Base64.getDecoder().decode(inputData);
        return new String(bytes);
    }
    private String addSalt(String inputData){
        //TODO Реализовать метод добавления соли в шифрование
        return null;
    }
}
