package ru.academits.bondyuk.shapes;

public class Triangle implements Shape {
    private double xCoordinatePoint1;
    private double yCoordinatePoint1;
    private double xCoordinatePoint2;
    private double yCoordinatePoint2;
    private double xCoordinatePoint3;
    private double yCoordinatePoint3;

    public Triangle(double xCoordinatePoint1, double yCoordinatePoint1, double xCoordinatePoint2, double yCoordinatePoint2,
                    double xCoordinatePoint3, double yCoordinatePoint3) {
        this.xCoordinatePoint1 = xCoordinatePoint1;
        this.yCoordinatePoint1 = yCoordinatePoint1;
        this.xCoordinatePoint2 = xCoordinatePoint2;
        this.yCoordinatePoint2 = yCoordinatePoint2;
        this.xCoordinatePoint3 = xCoordinatePoint3;
        this.yCoordinatePoint3 = yCoordinatePoint3;
    }

    @Override
    public double getWidth() {
        return Math.max(Math.max(xCoordinatePoint1, xCoordinatePoint2), xCoordinatePoint3)
                - Math.min(Math.min(xCoordinatePoint1, xCoordinatePoint2), xCoordinatePoint3);
    }

    @Override
    public double getHeight() {
        return Math.max(Math.max(yCoordinatePoint1, yCoordinatePoint2), yCoordinatePoint3)
                - Math.min(Math.min(yCoordinatePoint1, yCoordinatePoint2), yCoordinatePoint3);
    }

    @Override
    public double getArea() {
        return 1.0 / 2 * getHeight() * getWidth();
    }

    @Override
    public double getPerimeter() {
        double side12 = Math.sqrt(Math.pow(xCoordinatePoint1 - xCoordinatePoint2, 2) + Math.pow(yCoordinatePoint1 - yCoordinatePoint2, 2));
        double side23 = Math.sqrt(Math.pow(xCoordinatePoint2 - xCoordinatePoint3, 2) + Math.pow(yCoordinatePoint2 - yCoordinatePoint3, 2));
        double side13 = Math.sqrt(Math.pow(xCoordinatePoint1 - xCoordinatePoint3, 2) + Math.pow(yCoordinatePoint1 - yCoordinatePoint3, 2));

        return side12 + side23 + side13;
    }
}
