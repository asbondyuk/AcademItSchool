package ru.academits.bondyuk;

import ru.academits.bondyuk.model.TemperatureService;
import ru.academits.bondyuk.model.TemperatureTypes;

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
        try {
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (Exception e) {
            System.out.println("Что то пошло не так");
        }

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame frame = new JFrame("Конвертер температур");
                frame.setSize(300, 200);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

                JLabel fromText = new JLabel("Начальная шкала");
                JComboBox from = new JComboBox(TemperatureTypes.values());

                JLabel toText = new JLabel("Конечная шкала");
                JComboBox to = new JComboBox(TemperatureTypes.values());

                JLabel inputValueText = new JLabel("Значение для перевода");
                JTextField inputValue = new JTextField(5);

                JLabel resultText = new JLabel("Результат перевода");
                JLabel result = new JLabel("123");
//                result.setVisible(false);

                JButton convertButton = new JButton("Перевести");

                convertButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        result.setText("100500");
                        frame.add(result);
                    }
                });


                frame.setLayout(new FlowLayout(FlowLayout.CENTER));
                frame.add(fromText);
                frame.add(from);
                frame.add(toText);
                frame.add(to);
                frame.add(inputValueText);
                frame.add(inputValue);
                frame.add(convertButton);
                frame.add(resultText);

                frame.setVisible(true);
            }
        });
    }
}
