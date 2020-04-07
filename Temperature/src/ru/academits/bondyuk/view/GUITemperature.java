package ru.academits.bondyuk.view;

import ru.academits.bondyuk.model.TemperatureTypes;
import ru.academits.bondyuk.presenter.Presenter;

import javax.swing.*;
import java.awt.*;

public class GUITemperature extends JFrame {

    public void createUI() {
        try {
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (Exception e) {
            System.out.println("С установкой стилей что то пошло не так");
        }

        JFrame frame = new JFrame("Конвертер температур");
        frame.setLayout(new FlowLayout(FlowLayout.CENTER));
        frame.setSize(300, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel fromText = new JLabel("Начальная шкала");
        JComboBox from = new JComboBox(TemperatureTypes.values());

        JLabel toText = new JLabel("Конечная шкала");
        JComboBox to = new JComboBox(TemperatureTypes.values());

        JLabel inputValueText = new JLabel("Значение для перевода");
        JTextField inputValue = new JTextField(5);

        JLabel resultText = new JLabel("Результат перевода");
        JLabel result = new JLabel("     ");

        JButton convertButton = new JButton("Перевести");
        convertButton.addActionListener(e -> {
            if (!InputTextValidator.validate(inputValue.getText())) {
                JOptionPane.showMessageDialog(GUITemperature.this,
                        "Для перевода нужно ввести число! Получено: " + inputValue.getText(),
                        "Предупреждение",
                        JOptionPane.ERROR_MESSAGE);

                return;
            }

            double r = Presenter.getTemperature(inputValue, from, to);
            result.setText(ResultTemplate.getResult(r));
        });

        frame.add(fromText);
        frame.add(from);
        frame.add(toText);
        frame.add(to);
        frame.add(inputValueText);
        frame.add(inputValue);
        frame.add(convertButton);
        frame.add(resultText);
        frame.add(result);

        frame.setVisible(true);
    }
}
