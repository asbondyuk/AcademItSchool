package ru.academits.bondyuk;

import ru.academits.bondyuk.utils.DefaultTemperatureService;
import ru.academits.bondyuk.utils.TemperatureService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUITemperature {
    private GUITemperature() {
    }

    public static void createUI() {
        try {
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (Exception e) {
            System.out.println("Что то пошло не так");
        }

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame frame = new JFrame("Конвертер температур");
                frame.setSize(500, 200);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

                JLabel fromText = new JLabel("Начальная шкала");
                JComboBox from = new JComboBox(TemperatureTypes.values());

                System.out.println(from.getSelectedIndex());

                JLabel toText = new JLabel("Конечная шкала");
                JComboBox to = new JComboBox(TemperatureTypes.values());

                JLabel inputValueText = new JLabel("Значение для перевода");
                JTextField inputValue = new JTextField(5);

                JLabel resultText = new JLabel("Результат перевода");
                JLabel result = new JLabel("     ");


                JButton convertButton = new JButton("Перевести");
                convertButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        double r = Controller.getTemperature(inputValue, from, to);
                        result.setText(String.valueOf(r));
                        frame.add(result);
//                        frame.repaint();
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
                frame.add(result);

                frame.setVisible(true);
            }
        });
    }
}
