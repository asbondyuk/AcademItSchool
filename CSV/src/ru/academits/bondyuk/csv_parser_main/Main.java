package ru.academits.bondyuk.csv_parser_main;

import ru.academits.bondyuk.csv_parser.CsvToHtmlConverter;

public class Main {
    public static void main(String[] args) {
        String[] ar = new String[]{"in2.csv", "out2.html"};
        System.out.println("Разбор CSV файла");

        String inputFileName = ar[0];
        String outputFileName = ar[1];

        CsvToHtmlConverter.parseCSV(inputFileName, outputFileName);
    }
}