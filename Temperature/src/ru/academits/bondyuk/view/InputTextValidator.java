package ru.academits.bondyuk.view;

public class InputTextValidator {
    private InputTextValidator() {
    }

    public static boolean isNumeric(String inputText) {
        try {
            Double.parseDouble(inputText);
        } catch (NumberFormatException | NullPointerException e) {
            return false;
        }

        return true;
    }
}
