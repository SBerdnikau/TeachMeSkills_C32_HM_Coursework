package com.teachmeskills.finance_app.utils;

public interface Constants {
    String STATS_FILE_NAME = "src/com/teachmeskills/finance_app/resources/statistic/statistic.txt";
    String CHECK_REGEX = "Bill total amount EURO\\s*([0-9]*[.,][0-9]+)";
    String INVOICE_REGEX = "Total amount\\s*(\\d+)\\$";
    String ORDER_REGEX = "Order Total\\s*(\\d{1,3})(,\\d{3})*\\.(\\d{2})";
}
