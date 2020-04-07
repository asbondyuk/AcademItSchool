package ru.academits.bondyuk.model;

public interface TemperatureService {
    double getTemperature(double value, TemperatureTypes from, TemperatureTypes to);
}
