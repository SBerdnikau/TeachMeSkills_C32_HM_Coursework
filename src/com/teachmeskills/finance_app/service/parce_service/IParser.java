package com.teachmeskills.finance_app.service.parce_service;

import com.teachmeskills.finance_app.session.SessionManager;

import java.io.File;

public interface IParser {
    void validatorDocument(SessionManager session);
    double parsingDocument(File fileName);
}
