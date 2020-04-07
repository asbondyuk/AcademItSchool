package ru.academits.bondyuk.App;

import ru.academits.bondyuk.view.GUITemperature;

public class Main {
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(() -> {
            GUITemperature gui = new GUITemperature();
            gui.createUI();
        });
    }
}
