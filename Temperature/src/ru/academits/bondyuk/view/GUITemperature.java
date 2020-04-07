package ru.academits.bondyuk.view;

import ru.academits.bondyuk.model.TemperatureTypes;
import ru.academits.bondyuk.presenter.Presenter;

import javax.swing.*;
import java.awt.*;

public class GUITemperature extends JFrame {
    private JComboBox<String> from;
    private JComboBox<String> to;
    private JTextField inputValue;
    private JLabel result;

    public void createUI() {
        try {
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (Exception e) {
            System.out.println("С установкой стилей что то пошло не так");
        }

        JFrame frame = new JFrame("Конвертер температур");
        frame.setLayout(new FlowLayout(FlowLayout.CENTER));
        int height = 200;
        int width = 300;
        frame.setSize(width, height);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel fromText = new JLabel("Начальная шкала");
        from = new JComboBox(TemperatureTypes.values());

        JLabel toText = new JLabel("Конечная шкала");
        to = new JComboBox(TemperatureTypes.values());

        JLabel inputValueText = new JLabel("Значение для перевода");
        inputValue = new JTextField(5);

        JLabel resultText = new JLabel("Результат перевода");
        result = new JLabel("     ");

        JButton convertButton = new JButton("Перевести");
        convertButton.addActionListener(e -> convert());

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

    private void convert() {
        if (!InputTextValidator.isNumeric(inputValue.getText())) {
            JOptionPane.showMessageDialog(GUITemperature.this,
                    "Для перевода нужно ввести число! Получено: " + inputValue.getText(),
                    "Предупреждение",
                    JOptionPane.ERROR_MESSAGE);

            return;
        }

        double r = Presenter.getTemperature(inputValue, from, to);
        result.setText(ResultTemplate.getResult(r));
    }
}
