package ru.academits.bondyuk.utils;

import ru.academits.bondyuk.TemperatureTypes;

public interface TemperatureService {
    double getTemperature(double value, TemperatureTypes from, TemperatureTypes to);
}
