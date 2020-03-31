package ru.academits.bondyuk.csv_parser;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Parser {
    static final char mark = '\"';
    static final String defaultDelimiter = ",";

    private static boolean validateProblem(String line) {
        return line.indexOf(mark) != -1;
    }

    private static String[] splitLine(String line) {
        line = line.replace(">", "&gt;");
        line = line.replace("<", "&lt;");
        line = line.replace("&", "&amp;");

        if (!validateProblem(line)) {
            return line.split(defaultDelimiter);
        } else {
            boolean lineIsEnded = false;


        }

        return null;
    }

    public static void parseCSV(String inputFileName, String outputFileName) {
        try (Scanner scanner = new Scanner(new FileInputStream(inputFileName));
             PrintWriter printWriter = new PrintWriter(new File(outputFileName))) {
            printWriter.println("<table>");

            while (scanner.hasNext()) {
                printWriter.println("<tr>");

                String[] cellsValues = splitLine(scanner.nextLine());

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