package ru.academits.bondyuk;

import ru.academits.bondyuk.utils.DefaultTemperatureService;
import ru.academits.bondyuk.utils.TemperatureService;

import javax.swing.*;

public class Controller {
    public static double getTemperature(JTextField text, JComboBox from, JComboBox to) {
        String inputText = text.getText();

        double value = Double.parseDouble(inputText);
        TemperatureTypes fromSelectedIndex = TemperatureTypes.values()[from.getSelectedIndex()];
        TemperatureTypes toSelectedIndex = TemperatureTypes.values()[to.getSelectedIndex()];

        TemperatureService service = new DefaultTemperatureService();

        return service.getTemperature(value, fromSelectedIndex, toSelectedIndex);
    }
}
