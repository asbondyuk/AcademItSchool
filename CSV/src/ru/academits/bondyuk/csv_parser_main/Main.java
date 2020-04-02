package ru.academits.bondyuk.csv_parser_main;

import ru.academits.bondyuk.csv_parser.Parser;

public class Main {
    public static void main(String[] args) {
        System.out.println("Разбор CSV файла");

        String inputFileName = "in2.csv";
        String outputFileName = "out2.html";

        Parser.parseCSV(inputFileName, outputFileName);
    }
}