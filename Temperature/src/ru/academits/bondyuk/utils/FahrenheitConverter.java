package ru.academits.bondyuk.utils;

import ru.academits.bondyuk.TemperatureTypes;

public class FahrenheitConverter {
    private FahrenheitConverter() {
    }

    static double convert(double value, TemperatureTypes types) {
        switch (types) {
            case CELSIUS:
                return FahrenheitConverter.convertToCelsius(value);
            case KELVIN:
                return FahrenheitConverter.convertToKelvin(value);
        }

        return value;
    }

    private static double convertToCelsius(double value) {
        return 5 * (value - 32) / 9;
    }

    private static double convertToKelvin(double value) {
        return (value - 32) * 5 / 9 + 273.15;
    }
}
