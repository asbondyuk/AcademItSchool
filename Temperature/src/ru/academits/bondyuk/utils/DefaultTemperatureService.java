package ru.academits.bondyuk.utils;

import ru.academits.bondyuk.TemperatureTypes;

public class DefaultTemperatureService implements TemperatureService {
    @Override
    public double getTemperature(double value, TemperatureTypes from, TemperatureTypes to) {
        if (from == TemperatureTypes.CELSIUS) {
            return CelsiusConverter.convert(value, to);
        }

        if (from == TemperatureTypes.KELVIN) {
            return KelvinConverter.convert(value, to);
        }

        return FahrenheitConverter.convert(value, to);
    }
}
