package ru.academits.bondyuk.utils;

import ru.academits.bondyuk.TemperatureTypes;

public class KelvinConverter {
    private KelvinConverter() {
    }

    public static double convert(double value, TemperatureTypes types) {
        switch (types) {
            case FAHRENHEIT:
                return convertToFahrenheit(value);
            case CELSIUS:
                return convertToCelsius(value);
        }

        return value;
    }

    private static double convertToCelsius(double value) {
        return value - 273.15;
    }

    private static double convertToFahrenheit(double value) {
        return (value - 273.15) * 9 / 5 + 32;
    }
}
