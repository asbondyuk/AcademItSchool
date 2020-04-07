package ru.academits.bondyuk.view;

public class InputTextValidator {
    private InputTextValidator() {
    }

    public static boolean validate(String inputText) {
        try {
            double value = Double.parseDouble(inputText);
        } catch (NumberFormatException | NullPointerException e) {
            return false;
        }

        return true;
    }
}
