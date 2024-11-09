package com.teachmeskills.finance_app.service.parce_service;

import com.teachmeskills.finance_app.service.parce_service.statistic.Statistics;

import java.io.File;

public interface IParser {
    Statistics parsingDocument(File fileName);
}
