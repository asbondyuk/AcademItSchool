package ru.academits.bondyuk.csv_parser;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Parser {
    static final String defaultDelimiter = ",";

    private static String handleSpecialCharacter(String string) {
        string = string.replace(">", "&gt;")
                .replace("<", "&lt;")
                .replace("&", "&amp;");

        return string;
    }

    private static boolean isRowEnd(String line) {
        return countQuotes(line) % 2 == 0;
    }

    private static int countQuotes(String line) {
        int quotesCount = 0;

        for (int i = 0; i < line.length(); ++i) {
            if (line.charAt(i) == '"') {
                ++quotesCount;
            }
        }

        return quotesCount;
    }

    // hard-case line, line ended
    private static String[] splitLine(String line) {
        int leftDelimiterIndex = line.indexOf(defaultDelimiter);
        int rightDelimiterIndex = line.lastIndexOf(defaultDelimiter);
        System.out.println(line);

        String[] splittedLine = {
                line.substring(0, leftDelimiterIndex),
                line.substring(leftDelimiterIndex + 1, rightDelimiterIndex),
                line.substring(rightDelimiterIndex + 1)};

        return splittedLine;
    }

    public static void parseCSV(String inputFileName, String outputFileName) {
        try (Scanner scanner = new Scanner(new FileInputStream(inputFileName));
             PrintWriter printWriter = new PrintWriter(new File(outputFileName))) {
            printWriter.println("<table>");

            while (scanner.hasNext()) {
                printWriter.println("<tr>");

                String line = scanner.nextLine();
                String[] cellsValues;

                if (line.indexOf('"') != -1) {
                    while (!isRowEnd(line)) {
                        if (scanner.hasNext()) {
                            line = line + System.lineSeparator() + scanner.nextLine();
                        } else {
                            break;
                        }
                    }

                    line = handleSpecialCharacter(line);
                    cellsValues = splitLine(line);
                } else {
                    line = handleSpecialCharacter(line);
                    cellsValues = line.split(defaultDelimiter);
                }

                assert cellsValues != null;
                for (String value : cellsValues) {
                    printWriter.println("<td>" + value + "</td>");
                }

                printWriter.println("</tr>" + "</br>");
            }

            printWriter.println("</table>");
        } catch (FileNotFoundException e) {
            System.err.println(e.getMessage());
        }
    }
}