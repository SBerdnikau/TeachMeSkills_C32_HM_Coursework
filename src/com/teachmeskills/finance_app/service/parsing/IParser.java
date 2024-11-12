package com.teachmeskills.finance_app.service.parsing;

import com.teachmeskills.finance_app.session.SessionManager;

import java.io.File;

public interface IParser {
    void validatorDocument(String directoryPath, SessionManager session);
    double parsingDocument(File fileName);
}
