package ru.academits.bondyuk.main;

import ru.academits.bondyuk.range.Range;

public class Main {
    public static void main(String[] args) {
        System.out.println("Программа проверки класса Range");

        Range range = new Range(1.1, 9.9);

        double number = 5;

        System.out.println("Нижняя граница диапазона: " + range.getFrom());
        System.out.println("Верхняя граница диапазона: " + range.getTo());
        System.out.println("Длина диапазона: " + range.getLength());
        System.out.println(number + " находится в диапазоне: " + range.isInside(number));
    }
}
