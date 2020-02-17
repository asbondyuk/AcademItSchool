package ru.academits.bondyuk.main;

import ru.academits.bondyuk.hardrange.Range;

public class Main {
    public static void testAddedFunction(Range range1, Range range2) {
        System.out.printf("\nПроверка с (%.2f , %.2f) \n", range2.getFrom(), range2.getTo());

        testIntersectionRanges(range1, range2);
        testUnionRanges(range1, range2);
        testDifferenceRanges(range1, range2);
    }

    public static void testIntersectionRanges(Range range1, Range range2) {
        Range[] intersectionRange = range1.getIntersection(range2);

        if (intersectionRange.length > 1) {
            System.out.printf("Пересечения диапазонов (%.2f , %.2f) и (%.2f , %.2f) : (%.2f , %.2f) \n", range1.getFrom(), range1.getTo(),
                    range2.getFrom(), range2.getTo(), intersectionRange[0].getFrom(), intersectionRange[0].getTo());
        } else {
            System.out.printf("Пересечение диапазонов (%.2f , %.2f) и (%.2f , %.2f) отсутствует \n", range1.getFrom(), range1.getTo(),
                    range2.getFrom(), range2.getTo());
        }
    }

    public static void testUnionRanges(Range range1, Range range2) {
        Range[] unionRanges = range1.getUnion(range2);

        if (unionRanges.length > 1) {
            System.out.printf("Объединение диапазонов (%.2f , %.2f) и (%.2f , %.2f) : (%.2f , %.2f) и (%.2f , %.2f) \n", range1.getFrom(), range1.getTo(),
                    range2.getFrom(), range2.getTo(), unionRanges[0].getFrom(), unionRanges[0].getTo(), unionRanges[0].getFrom(), unionRanges[0].getTo());
        } else {
            System.out.printf("Объединение диапазонов (%.2f , %.2f) и (%.2f , %.2f): (%.2f , %.2f) \n", range1.getFrom(), range1.getTo(),
                    range2.getFrom(), range2.getTo(), unionRanges[0].getFrom(), unionRanges[0].getTo());
        }
    }

    public static void testDifferenceRanges(Range range1, Range range2) {
        Range[] differenceRanges = range1.getDifference(range2);

        if (differenceRanges.length == 0) {
            System.out.printf("Разница диапазонов (%.2f , %.2f) и (%.2f , %.2f) : пустой диапазон \n", range1.getFrom(), range1.getTo(),
                    range2.getFrom(), range2.getTo());
        } else if (differenceRanges.length > 1) {
            System.out.printf("Разница диапазонов (%.2f , %.2f) и (%.2f , %.2f) : (%.2f , %.2f) и (%.2f , %.2f) \n", range1.getFrom(), range1.getTo(),
                    range2.getFrom(), range2.getTo(), differenceRanges[0].getFrom(), differenceRanges[0].getTo(), differenceRanges[1].getFrom(), differenceRanges[1].getTo());
        } else {
            System.out.printf("Разница диапазонов (%.2f , %.2f) и (%.2f , %.2f): (%.2f , %.2f) \n", range1.getFrom(), range1.getTo(),
                    range2.getFrom(), range2.getTo(), differenceRanges[0].getFrom(), differenceRanges[0].getTo());
        }
    }

    public static void main(String[] args) {
        System.out.println("Программа проверки класса Range");

        Range range1 = new Range(1.1, 9.9);

        double number = 5;

        System.out.println("Нижняя граница диапазона: " + range1.getFrom());
        System.out.println("Верхняя граница диапазона: " + range1.getTo());
        System.out.println("Длина диапазона: " + range1.getLength());
        System.out.println(number + " находится в диапазоне: " + range1.isInside(number) + "\n");

        System.out.println("Проверка дополнительных функций");

        // Пример 1
        Range range2 = new Range(0, 20);
        testAddedFunction(range1, range2);

        // Пример 2
        Range range3 = new Range(4, 6);
        testAddedFunction(range1, range3);

        // Пример 3
        Range range4 = new Range(0, 6);
        testAddedFunction(range1, range4);

        // Пример 4
        Range range5 = new Range(6, 20);
        testAddedFunction(range1, range5);
    }
}
