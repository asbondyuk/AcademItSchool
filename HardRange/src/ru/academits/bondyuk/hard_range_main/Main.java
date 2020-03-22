package ru.academits.bondyuk.hard_range_main;

import ru.academits.bondyuk.hard_range.Range;

import java.util.Arrays;

public class Main {
    public static void testAddedFunctions(Range range1, Range range2) {
        System.out.printf("%nПроверка с %s%n", range2);

        testRangesIntersection(range1, range2);
        testRangesUnion(range1, range2);
        testRangesDifference(range1, range2);
    }

    public static void testRangesIntersection(Range range1, Range range2) {
        Range intersectionRange = range1.getIntersection(range2);

        System.out.printf("Пересечение диапазонов %s и %s : %s%n", range1, range2, intersectionRange);
    }

    public static void testRangesUnion(Range range1, Range range2) {
        Range[] unionRanges = range1.getUnion(range2);

        System.out.printf("Объединение диапазонов %s и %s: %s%n", range1, range2, getRangesArrayAsString(unionRanges));
    }

    public static void testRangesDifference(Range range1, Range range2) {
        Range[] differenceRanges = range1.getDifference(range2);

        System.out.printf("Разница диапазонов %s и %s: %s %n", range1, range2, getRangesArrayAsString(differenceRanges));
    }

    public static String getRangesArrayAsString(Range[] ranges) {
        return Arrays.toString(ranges);
    }

    public static void main(String[] args) {
        System.out.println("Программа проверки класса Range");

        Range range1 = new Range(1.1, 9.9);

        double number = 5;

        System.out.printf("Нижняя граница диапазона: %.2f%n", range1.getFrom());
        System.out.printf("Верхняя граница диапазона: %.2f%n", range1.getTo());
        System.out.printf("Длина диапазона: %.2f%n", range1.getLength());
        System.out.printf("%.2f находится в диапазоне: %b%n", number, range1.isInside(number));

        System.out.println("Проверка дополнительных функций");

        // Пример 1
        Range range2 = new Range(0, 20);
        testAddedFunctions(range1, range2);

        // Пример 2
        Range range3 = new Range(4, 6);
        testAddedFunctions(range1, range3);

        // Пример 3
        Range range4 = new Range(0, 6);
        testAddedFunctions(range1, range4);

        // Пример 4
        Range range5 = new Range(6, 20);
        testAddedFunctions(range1, range5);

        // Пример 5
        Range range6 = new Range(16, 20);
        testAddedFunctions(range1, range6);

        // Пример 6
        Range range7 = new Range(1, 3);
        Range range8 = new Range(5, 7);
        testAddedFunctions(range7, range8);

        // Пример 6
        Range range9 = new Range(1, 5);
        Range range10 = new Range(3, 7);
        testAddedFunctions(range9, range10);

        // Пример 7
        Range range11 = new Range(1, 3);
        Range range12 = new Range(1, 5);
        testAddedFunctions(range11, range12);
        testAddedFunctions(range12, range11);

        // Пример 8
        Range range13 = new Range(1, 7);
        Range range14 = new Range(1, 7);
        testAddedFunctions(range13, range14);

        // Пример 9
        Range range15 = new Range(3, 5);
        Range range16 = new Range(1, 7);
        testAddedFunctions(range15, range16);
    }
}