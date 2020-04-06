package ru.academits.bondyuk;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {
//    public static void main(String[] args) {
//        System.out.println(TemperatureService.convert(100, TemperatureTypes.CELSIUS, TemperatureTypes.FAHRENHEIT));
//        System.out.println(TemperatureService.convert(100, TemperatureTypes.CELSIUS, TemperatureTypes.CELSIUS));
//        System.out.println(TemperatureService.convert(100, TemperatureTypes.KELVIN, TemperatureTypes.CELSIUS));
//        System.out.println(TemperatureService.convert(100, TemperatureTypes.KELVIN, TemperatureTypes.FAHRENHEIT));
//    }

    public static void main(String[] args) {

        GUITemperature gui = new GUITemperature();

        gui.createUI();
    }
}
