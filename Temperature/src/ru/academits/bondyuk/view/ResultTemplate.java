package ru.academits.bondyuk.view;

import java.text.DecimalFormat;

public class ResultTemplate {
    private ResultTemplate() {
    }

    public static String getResult(double value) {
        return new DecimalFormat("#0.00").format(value);
    }
}
