package ru.academits.bondyuk.array_list_home_main;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ArrayListHome {
    private ArrayListHome() {
    }

    public static List<String> getFileLines(String filePath) {
        List<String> lines = new ArrayList<>();

        try (Scanner scanner = new Scanner(new File(filePath))) {
            while (scanner.hasNextLine()) {
                lines.add(scanner.nextLine());
            }
        } catch (FileNotFoundException e) {
            System.err.println("Не найден файл " + filePath);
        }

        return lines;
    }
}