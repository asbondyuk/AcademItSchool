package ru.academits.bondyuk.utils;

import ru.academits.bondyuk.TemperatureTypes;

public class CelsiusConverter {
    private CelsiusConverter() {
    }

    public static double convert(double value, TemperatureTypes types) {
        switch (types) {
            case FAHRENHEIT:
                return convertToFahrenheit(value);
            case KELVIN:
                return convertToKelvin(value);
        }

        return value;
    }

    private static double convertToFahrenheit(double value) {
        return 9 * value / 5 + 32;
    }

    private static double convertToKelvin(double value) {
        return value + 273.15;
    }
}
