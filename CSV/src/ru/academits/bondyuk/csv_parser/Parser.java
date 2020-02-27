package ru.academits.bondyuk.csv_parser;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Parser {
    public static void parseCSV(String inputFileName, String outputFileName) {
        String defaultDelimiter = ",";

        try (Scanner scanner = new Scanner(new FileInputStream(inputFileName));
             PrintWriter printWriter = new PrintWriter(new File(outputFileName))) {

            printWriter.println("<table>");

            while (scanner.hasNext()) {
                printWriter.println("<tr>");

                String line = scanner.nextLine();
                String[] array = line.split(defaultDelimiter);

                if (array.length > 0) {
                    for (String string : array) {
                        printWriter.println("<td>" + string + "</td>");
                    }
                }

                printWriter.println("</tr>");
                printWriter.println("</br>");
            }

            printWriter.println("</table>");
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
}