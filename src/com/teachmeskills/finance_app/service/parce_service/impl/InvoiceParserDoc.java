package com.teachmeskills.finance_app.service.parce_service.impl;

import com.teachmeskills.finance_app.service.parce_service.IParser;
import com.teachmeskills.finance_app.service.parce_service.statistic.Statistics;

import java.io.File;

public class InvoiceParserDoc implements IParser {
    @Override
    public Statistics parsingDocument(File fileName) {
        //TODO Логика обработки документов. Сбор статистики по инвойсам
        return null;
    }
}
