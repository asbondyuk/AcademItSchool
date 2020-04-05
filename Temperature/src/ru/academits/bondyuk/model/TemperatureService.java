package ru.academits.bondyuk.model;

public class TemperatureService {
    private TemperatureService() {
    }

    // ToDo  Исправить лапше-код(?)
    public static double convert(double value, TemperatureTypes from, TemperatureTypes to) {
        if (from.equals(TemperatureTypes.CELSIUS)) {
            switch (to) {
                case FAHRENHEIT:
                    return convertFromCelsiusToFahrenheit(value);
                case KELVIN:
                    return convertFromCelsiusToKelvin(value);
                case CELSIUS:
                    return value;
            }
        } else if (from.equals(TemperatureTypes.FAHRENHEIT)) {
            switch (to) {
                case FAHRENHEIT:
                    return value;
                case KELVIN:
                    return convertFromFahrenheitToKelvin(value);
                case CELSIUS:
                    return convertFromFahrenheitToCelsius(value);
            }
        } else {
            switch (to) {
                case FAHRENHEIT:
                    return convertFromKelvinToFahrenheit(value);
                case KELVIN:
                    return value;
                case CELSIUS:
                    return convertFromKelvinToCelsius(value);
            }
        }

        return 9999999;
    }

    private static double convertFromCelsiusToFahrenheit(double value) {
        return 9 * value / 5 + 32;
    }

    private static double convertFromCelsiusToKelvin(double value) {
        return value + 273.15;
    }

    private static double convertFromFahrenheitToCelsius(double value) {
        return 5 * (value - 32) / 9;
    }

    private static double convertFromFahrenheitToKelvin(double value) {
        return (value - 32) * 5 / 9 + 273.15;
    }

    private static double convertFromKelvinToCelsius(double value) {
        return value - 273.15;
    }

    private static double convertFromKelvinToFahrenheit(double value) {
        return (value - 273.15) * 9 / 5 + 32;
    }
}
