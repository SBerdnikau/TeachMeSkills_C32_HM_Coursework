package com.teachmeskills.finance_app.session;

import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.stream.Collectors;

public class SessionManager {

    private String accessToken;
    private Date expDate;

    public SessionManager() {
        setAccessToken();
        setExpDate();
    }

    /**
     *to check if the session is valid
     * @return session validity
     */
    public boolean isSessionValid(){
        return  this.accessToken.length() == 16 && this.expDate.after(new Date());
    }

    /**
     * to generate a token
     */
    private void setAccessToken() {
        String symbols = "abcdefghijklmnopqrstuvwxyz0123456789";

        this.accessToken =  new Random().ints(16, 0, symbols.length())
                .mapToObj(symbols::charAt)
                .map(Object::toString)
                .collect(Collectors.joining());
    }

    /**
     * to set the session life date
     */
    private void setExpDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.MINUTE, 1);
        this.expDate = calendar.getTime();
    }
}
