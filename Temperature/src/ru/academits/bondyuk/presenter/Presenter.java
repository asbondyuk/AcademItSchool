package ru.academits.bondyuk.presenter;

import ru.academits.bondyuk.model.TemperatureTypes;
import ru.academits.bondyuk.model.DefaultTemperatureService;
import ru.academits.bondyuk.model.TemperatureService;

import javax.swing.*;

public class Presenter {
    public static double getTemperature(JTextField text, JComboBox<String> from, JComboBox<String> to) {
        String inputText = text.getText();

        double value = Double.parseDouble(inputText);
        TemperatureTypes fromSelectedIndex = TemperatureTypes.values()[from.getSelectedIndex()];
        TemperatureTypes toSelectedIndex = TemperatureTypes.values()[to.getSelectedIndex()];

        TemperatureService service = new DefaultTemperatureService();

        return service.getTemperature(value, fromSelectedIndex, toSelectedIndex);
    }
}
