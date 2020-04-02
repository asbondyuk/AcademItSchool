package ru.academits.bondyuk.csv_parser;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Parser {
    static final String defaultDelimiter = ",";

    private static String handleSpecialCharacter(String string) {
        return string.replace(">", "&gt;")
                .replace("<", "&lt;")
                .replace("&", "&amp;");
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
    private static List<String> splitLine(String line) {
        List<String> strings = new ArrayList<>();

        boolean isContainQuotes = false;
        int startIndex = 0;

        for (int i = 0; i < line.length() - 1; ++i) {
            if (line.charAt(0) == '"') {
                isContainQuotes = true;
                continue;
            }

            if (line.charAt(i) == '"' & line.charAt(i + 1) == '"' & isContainQuotes) {
                ++i;
                continue;
            }

            if (line.charAt(i) == '"' & line.charAt(i + 1) == ',' & isContainQuotes) {
                strings.add(line.substring(startIndex, i));
                startIndex = i + 2;
                isContainQuotes = false;
                ++i;
                continue;
            }

            if (line.charAt(i) == ',' & line.charAt(i + 1) == '"' & !isContainQuotes) {
                strings.add(line.substring(startIndex, i));
                startIndex = i + 2;
                isContainQuotes = true;
                ++i;
                continue;
            }

            if (i + 2 == line.length() & line.charAt(i + 1) == '"') {
                strings.add(line.substring(startIndex + 1, line.length() - 2));
                continue;
            }
//
//            if (i + 2 == line.length()& line.charAt(i + 1) != '"') {
//                strings.add(line.substring(startIndex));
//            }
        }

        if (line.charAt(line.length() - 1) != '"') {
            strings.add(line.substring(startIndex));
        }


        for (int i = 0; i < strings.size(); ++i) {
            if (strings.get(i).contains("\"\"")) {
                strings.set(i, strings.get(i).replace("\"\"", "\""));
                ++i;
            }
        }

        return strings;
    }

    public static void parseCSV(String inputFileName, String outputFileName) {
        try (Scanner scanner = new Scanner(new FileInputStream(inputFileName));
             PrintWriter printWriter = new PrintWriter(new File(outputFileName))) {

            printWriter.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01//EN\" \"http://www.w3.org/TR/html4/strict.dtd\">\n" +
                    "<html>\n" +
                    " <head>\n" +
                    "  <meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\">\n" +
                    "  <title>Таблица</title>\n" +
                    "  <style type=\"text/css\">\n" +
                    "   TABLE {\n" +
                    "    width: 300px; /* Ширина таблицы */\n" +
                    "    border-collapse: collapse; /* Убираем двойные линии между ячейками */\n" +
                    "   }\n" +
                    "   TD, TH {\n" +
                    "    padding: 3px; /* Поля вокруг содержимого таблицы */\n" +
                    "    border: 1px solid black; \n" +
                    "   }\n" +
                    "   TH {\n" +
                    "    background: #b0e0e6; \n" +
                    "   }\n" +
                    "  </style>\n" +
                    " </head>\n" +
                    " <body>");
            printWriter.println("<table>");

            while (scanner.hasNext()) {
                printWriter.println("<tr>");

                String line = scanner.nextLine();
                List<String> cellsValues;

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

                    System.out.println(cellsValues);

                } else {
                    line = handleSpecialCharacter(line);
                    cellsValues = Arrays.asList(line.split(defaultDelimiter));
                }

                for (String value : cellsValues) {
                    printWriter.println("<td>" + value + "</td>");
                }

                printWriter.println("</tr>" + "</br>");
            }

            printWriter.println("</table>");
            printWriter.println("</body>");
        } catch (FileNotFoundException e) {
            System.err.println(e.getMessage());
        }
    }
}