package ru.academits.bondyuk.shapes;

import java.util.Objects;

public class Triangle implements Shape {
    private double x1;
    private double y1;
    private double x2;
    private double y2;
    private double x3;
    private double y3;

    public Triangle(double x1, double y1, double x2, double y2, double x3, double y3) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.x3 = x3;
        this.y3 = y3;
    }

    private static double getSideLength(double x1, double y1, double x2, double y2) {
        return Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
    }

    @Override
    public double getWidth() {
        return Math.max(Math.max(x1, x2), x3) - Math.min(Math.min(x1, x2), x3);
    }

    @Override
    public double getHeight() {
        return Math.max(Math.max(y1, y2), y3) - Math.min(Math.min(y1, y2), y3);
    }

    @Override
    public double getArea() {
        double side12 = getSideLength(x1, y1, x2, y2);
        double side23 = getSideLength(x2, y2, x3, y3);
        double side13 = getSideLength(x1, y1, x3, y3);
        double halfPerimeter = getPerimeter() / 2;

        return Math.sqrt(halfPerimeter * (halfPerimeter - side12) * (halfPerimeter - side23) * (halfPerimeter - side13));
    }

    @Override
    public double getPerimeter() {
        double side12 = getSideLength(x1, y1, x2, y2);
        double side23 = getSideLength(x2, y2, x3, y3);
        double side13 = getSideLength(x1, y1, x3, y3);

        return side12 + side23 + side13;
    }

    @Override
    public String toString() {
        return "Triangle{" +
                "x1=" + x1 +
                ", y1=" + y1 +
                ", x2=" + x2 +
                ", y2=" + y2 +
                ", x3=" + x3 +
                ", y3=" + y3 +
                ", perimeter=" + getPerimeter() +
                ", area=" + getArea() +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Triangle triangle = (Triangle) o;
        return (triangle.x1 == x1) &&
                (triangle.y1 == y1) &&
                (triangle.x2 == x2) &&
                (triangle.y1 == y1) &&
                (triangle.x3 == x3) &&
                (triangle.y3 == y3);
    }

    @Override
    public int hashCode() {
        return Objects.hash(x1, y1, x2, y2, x3, y3);
    }
}
