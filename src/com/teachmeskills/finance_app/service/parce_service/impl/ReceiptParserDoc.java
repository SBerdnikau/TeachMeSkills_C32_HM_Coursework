package com.teachmeskills.finance_app.service.parce_service.impl;

import com.teachmeskills.finance_app.service.parce_service.IParser;
import com.teachmeskills.finance_app.session.SessionManager;

import java.io.File;

public class ReceiptParserDoc implements IParser {


    @Override
    public void validatorOrder(SessionManager session) {
        //TODO Логика обработки документов. Сбор статистики по  чекам
    }

    @Override
    public double parsingDocument(File fileName) {
        return 0;
    }
}
