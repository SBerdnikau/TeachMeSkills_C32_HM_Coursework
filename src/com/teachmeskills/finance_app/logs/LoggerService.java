package com.teachmeskills.finance_app.logs;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

public class LoggerService {

    public LoggerService() {
    }

    public static void logInfo(String message) {
        System.out.println("Info-logger: " + message);
        try(BufferedWriter infoLogger = new BufferedWriter(new FileWriter("src/com/teachmeskills/finance_app/logs/resource_logs/info_log.txt", true))) {
            infoLogger.write("Info-logger: " + message + "\t" + new Date());
            infoLogger.newLine();
        }catch (IOException e){
            throw new RuntimeException("Exception IO: unable to write data to file");
        }
    }

    public static void logError(String message) {
        System.out.println("Error-logger: " + message);
        try(BufferedWriter errorLogger = new BufferedWriter(new FileWriter("src/com/teachmeskills/finance_app/logs/resource_logs/error_log.txt", true))) {
            errorLogger.write("Error logger: " + message + "\t" + new Date());
            errorLogger.newLine();
        } catch (IOException e) {
            throw new RuntimeException("Exception IO: unable to write data to file");
        }
    }
}
