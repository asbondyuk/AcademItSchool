package ru.academits.bondyuk.hard_range_main;

import ru.academits.bondyuk.hard_range.Range;

public class Main {
    public static void addedFunctionTest(Range range1, Range range2) {
        System.out.printf("%nПроверка с %s%n", range2);

        intersectionRangesTest(range1, range2);
        unionRangesTest(range1, range2);
        differenceRangesTest(range1, range2);
    }

    public static void intersectionRangesTest(Range range1, Range range2) {
        Range intersectionRange = range1.getIntersection(range2);

        System.out.printf("Пересечение диапазонов %s и %s : %s%n", range1, range2, intersectionRange);
    }

    public static void unionRangesTest(Range range1, Range range2) {
        Range[] unionRanges = range1.getUnion(range2);

        if (unionRanges.length > 1) {
            System.out.printf("Объединение диапазонов %s и %s : %s%n", range1, range2, unionRanges[0]);
        } else {
            System.out.printf("Объединение диапазонов %s и %s: %s%n", range1, range2, unionRanges[0]);
        }
    }

    public static void differenceRangesTest(Range range1, Range range2) {
        Range[] differenceRanges = range1.getDifference(range2);

        if (differenceRanges.length == 0) {
            System.out.printf("Разница диапазонов %s и %s : пустой диапазон %n", range1, range2);
        } else if (differenceRanges.length > 1) {
            System.out.printf("Разница диапазонов %s и %s :  %s и %s %n", range1, range2, differenceRanges[0], differenceRanges[1]);
        } else {
            System.out.printf("Разница диапазонов %s и %s: %s %n", range1, range2, differenceRanges[0]);
        }
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
        addedFunctionTest(range1, range2);

        // Пример 2
        Range range3 = new Range(4, 6);
        addedFunctionTest(range1, range3);

        // Пример 3
        Range range4 = new Range(0, 6);
        addedFunctionTest(range1, range4);

        // Пример 4
        Range range5 = new Range(6, 20);
        addedFunctionTest(range1, range5);

        // Пример 5
        Range range6 = new Range(16, 20);
        addedFunctionTest(range1, range6);
    }
}