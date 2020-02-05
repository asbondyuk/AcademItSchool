package ru.academits.bondyuk.shapes;

import java.util.ArrayList;
import java.util.Comparator;

public class Main {
    public static void main(String[] args) {
        ArrayList<Shape> shapes = new ArrayList<>();

        shapes.add(new Triangle(0, 0, 0, 2, 2, 0));
        shapes.add(new Circle(3.2));
        shapes.add(new Square(4));
        shapes.add(new Rectangle(60, 4));
        shapes.add(new Rectangle(4, 4));

        // Поверка площади
        for (Shape shape : shapes) {
            System.out.println(shape.getClass() + "  площадь составляет: " + shape.getArea() + ", периметр: " + shape.getPerimeter());
        }

        Comparator<Shape> areaComparator = Comparator.comparing(Shape::getArea);

        shapes.sort(areaComparator);
        System.out.println("\nМаксимальная площадь: " + shapes.get(shapes.size() - 1).getArea());

        // Проверка периметра
        Comparator<Shape> perimeterComparator = Comparator.comparing(Shape::getPerimeter);

        shapes.sort(perimeterComparator);
        System.out.println("\nВторой по величине периметр: " + shapes.get(shapes.size() - 2).getPerimeter());
    }
}
