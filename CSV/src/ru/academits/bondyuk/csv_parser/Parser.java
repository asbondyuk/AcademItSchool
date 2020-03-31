package ru.academits.bondyuk.csv_parser;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
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

    private static boolean isNeedSeparation(String string) {
        return string.indexOf("\"") == 0 & string.lastIndexOf("\"") == string.length() - 1;
    }

    // hard-case line, line ended
    private static String[] splitLine(String line) {
        int leftDelimiterIndex = line.indexOf(defaultDelimiter);
        int rightDelimiterIndex = line.lastIndexOf(defaultDelimiter);

        String[] splittedLine = {
                line.substring(0, leftDelimiterIndex),
                line.substring(leftDelimiterIndex + 1, rightDelimiterIndex),
                line.substring(rightDelimiterIndex + 1)};

        for (int i = 0; i < splittedLine.length; ++i) {
            if (isNeedSeparation(splittedLine[i])) {
                splittedLine[i] = splittedLine[i].substring(1, splittedLine[i].length() - 1);
                --i;
            }
        }

        for (int i=0; i< splittedLine.length;++i) {
            if (splittedLine[i].contains("\"\"")) {
                splittedLine[i] = splittedLine[i].replace("\"\"", "\"");
            }
        }

        return splittedLine;
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
            printWriter.println("</body>");
        } catch (FileNotFoundException e) {
            System.err.println(e.getMessage());
        }
    }
}