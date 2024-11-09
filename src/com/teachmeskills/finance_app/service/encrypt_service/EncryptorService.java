package com.teachmeskills.finance_app.service.encrypt_service;

import com.teachmeskills.finance_app.logs.LoggerService;

import java.util.Base64;
import java.util.Random;
import java.util.stream.Collectors;

public class EncryptorService {
    //TODO Реализовать шифрование с солью +
    public static String encrypt(String input){
        String encryptedString = Base64.getEncoder().encodeToString(input.getBytes());
        String result = addSalt(encryptedString);
        return result;
    }

    public static String decrypt(String input){
        LoggerService.logInfo("Идёт дешифрование входных данных пользователя...");
        byte[] bytes = Base64.getDecoder().decode(input.substring(20));
        String result = new String(bytes);
        return result;
    }

    private static String addSalt(String input){
        String symbols = "abcdefghijklmnopqrstuvwxyz0123456789";

        String salt =  new Random()
                .ints(20, 0, symbols.length())
                .mapToObj(symbols::charAt)
                .map(Object::toString)
                .collect(Collectors.joining());

        String result = salt + input;
        return  result;
    }
}
