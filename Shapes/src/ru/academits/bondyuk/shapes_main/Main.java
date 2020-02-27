package ru.academits.bondyuk.shapes_main;

import ru.academits.bondyuk.comparators.AreaComparator;
import ru.academits.bondyuk.comparators.PerimeterComparator;
import ru.academits.bondyuk.shapes.*;

import java.util.ArrayList;
import java.util.Comparator;

public class Main {
    public static void printShapeInformation(Shape shape) {
        System.out.printf("%s%nПлощадь: %f, %nПериметр: %f, %nВысота: %f, %nШирина: %f %n",
                shape.getClass().getSimpleName(), shape.getArea(), shape.getPerimeter(), shape.getHeight(), shape.getWidth());
    }

    public static void main(String[] args) {
        ArrayList<Shape> shapes = new ArrayList<>();

        shapes.add(new Triangle(0, 0, 0, 2, 2, 0));
        shapes.add(new Circle(3.2));
        shapes.add(new Square(4));
        shapes.add(new Rectangle(60, 4));
        shapes.add(new Rectangle(4, 6));
        shapes.add(new Rectangle(4, 6));

        for (Shape shape : shapes) {
            System.out.printf("%s площадь составляет: %.2f, периметр: %.2f%n", shape.getClass().getSimpleName(), shape.getArea(), shape.getPerimeter());
        }

        // тест override
        System.out.printf("%n" + shapes.get(1));

        System.out.printf("Сравнение %s и %s : %b%n", shapes.get(2), shapes.get(4),
                shapes.get(2).equals(shapes.get(4)));

        System.out.printf("Сравнение (одного объекта) %s и %s : %b%n", shapes.get(4), shapes.get(4),
                shapes.get(4).equals(shapes.get(4)));

        System.out.printf("Сравнение (разных объектов) %s и %s : %b%n", shapes.get(4), shapes.get(5),
                shapes.get(4).equals(shapes.get(5)));

        // Поверка площади v1
        Comparator<Shape> areaComparator1 = Comparator.comparing(Shape::getArea);

        shapes.sort(areaComparator1);

        System.out.printf("%nМаксимальная площадь у фигуры: ");

        int maxAreaShapeIndex = shapes.size() - 1;
        printShapeInformation(shapes.get(maxAreaShapeIndex));

        // Поверка площади v2
        AreaComparator areaComparator2 = new AreaComparator();

        shapes.sort(areaComparator2);

        System.out.printf("%nМаксимальная площадь у фигуры: ");

        printShapeInformation(shapes.get(maxAreaShapeIndex));

        // Проверка периметра v1
        Comparator<Shape> perimeterComparator1 = Comparator.comparing(Shape::getPerimeter);

        shapes.sort(perimeterComparator1);

        System.out.printf("%nВторой по величине периметр у фигуры: ");

        int secondMaxPerimeterShapeIndex = shapes.size() - 2;
        printShapeInformation(shapes.get(secondMaxPerimeterShapeIndex));

        // Проверка периметра v2
        shapes.sort(new PerimeterComparator());

        System.out.printf("%nВторой по величине периметр у фигуры: ");

        printShapeInformation(shapes.get(secondMaxPerimeterShapeIndex));
    }
}
