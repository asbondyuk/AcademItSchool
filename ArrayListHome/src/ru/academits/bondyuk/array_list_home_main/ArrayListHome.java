package ru.academits.bondyuk.array_list_home_main;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ArrayListHome {
    private String fileName;
    private List<String> rows;

    public ArrayListHome(String fileName) {
        this.fileName = fileName;
        rows = new ArrayList<>();
    }

    public List<String> getFileRows() {
        try (Scanner scanner = new Scanner(new File(fileName))) {
            while (scanner.hasNextLine()) {
                rows.add(scanner.nextLine());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return rows;
    }
}