package ru.academits.bondyuk.csv_parser;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Parser {
    static final char defaultDelimiter = ',';
    static final char quotationMarks = '"';

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

                while (!isRowEnd(line)) {
                    if (scanner.hasNext()) {
                        line = line + System.lineSeparator() + scanner.nextLine();
                    } else {
                        break;
                    }
                }

                line = handleSpecialCharacter(line);
                cellsValues = splitLine(line);

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

    private static boolean isRowEnd(String line) {
        return countQuotes(line) % 2 == 0;
    }

    private static int countQuotes(String line) {
        int quotesCount = 0;

        for (int i = 0; i < line.length(); ++i) {
            if (line.charAt(i) == quotationMarks) {
                ++quotesCount;
            }
        }

        return quotesCount;
    }

    private static String handleSpecialCharacter(String string) {
        return string.replace(">", "&gt;")
                .replace("<", "&lt;")
                .replace("&", "&amp;");
    }

    private static List<String> splitLine(String line) {
        List<String> strings = new ArrayList<>();

        boolean isQuotesCell = false;
        int startIndex = 0; // начальный индекс элемента для записи ячейки

        // провека разделителей
        for (int i = 0; i < line.length() - 1; ++i) {
            if (line.charAt(0) == quotationMarks) {
                isQuotesCell = true;
                continue;
            }

            if (isQuotesCell & line.charAt(i) == quotationMarks & line.charAt(i + 1) == quotationMarks) {
                ++i;
                continue;
            }

            if (isQuotesCell & line.charAt(i) == quotationMarks & line.charAt(i + 1) == defaultDelimiter) {
                strings.add(line.substring(startIndex, i));

                startIndex = i + 2;
                isQuotesCell = false;
                ++i;
                continue;
            }

            if (line.charAt(i) == defaultDelimiter & line.charAt(i + 1) == quotationMarks & !isQuotesCell) {
                strings.add(line.substring(startIndex, i));

                startIndex = i + 2;
                isQuotesCell = true;
                ++i;
                continue;
            }

            if (i + 2 == line.length() & line.charAt(i + 1) == quotationMarks) {
                strings.add(line.substring(startIndex + 1, line.length() - 2));

                continue;
            }

            if (!isQuotesCell & line.charAt(i) == ',') {
                strings.add(line.substring(startIndex, i));

                startIndex = i + 1;
            }
        }

        // добавляем последюю ячейку, без разделителя
        if (line.charAt(line.length() - 1) != quotationMarks) {
            strings.add(line.substring(startIndex));
        }

        getWithoutQuotesEscaping(strings);

        return strings;
    }

    private static void getWithoutQuotesEscaping(List<String> stringsList) {
        for (int i = 0; i < stringsList.size(); ++i) {
            if (stringsList.get(i).contains("\"\"")) {
                stringsList.set(i, stringsList.get(i).replace("\"\"", "\""));
                ++i;
            }
        }
    }
}