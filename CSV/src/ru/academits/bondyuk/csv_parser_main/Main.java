package ru.academits.bondyuk.csv_parser_main;

import ru.academits.bondyuk.csv_parser.Parser;

public class Main {
    public static void main(String[] args) {
        String inputFileName = "in2.csv";
        String outputFileName = "out21.html";

        Parser.parseCSV(inputFileName, outputFileName);
    }
}