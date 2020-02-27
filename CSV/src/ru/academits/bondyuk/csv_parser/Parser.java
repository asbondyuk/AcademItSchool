package ru.academits.bondyuk.csv_parser;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Parser {
    static final String defaultDelimiter = ",";

    private static String getRaw(Scanner scanner) {
        StringBuilder stringBuilder = new StringBuilder();
        String line = scanner.nextLine();

        while (true) {
            stringBuilder.append(line);

            if (line.contains("\"" + defaultDelimiter)) {
                line = stringBuilder.toString();
                break;
            }

            line = scanner.nextLine();
        }

        return line;
    }

    public static void parseCSV(String inputFileName, String outputFileName) {


        try (Scanner scanner = new Scanner(new FileInputStream(inputFileName));
             PrintWriter printWriter = new PrintWriter(new File(outputFileName))) {

            printWriter.println("<table>");

            while (scanner.hasNext()) {
                printWriter.println("<tr>");

                String line = getRaw(scanner);

                String[] array = line.split(defaultDelimiter);

                for (String string : array) {
                    printWriter.println("<td>" + string + "</td>");
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