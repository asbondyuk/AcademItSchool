package ru.academits.bondyuk.main;

import ru.academits.bondyuk.vector.Vector;

public class Main {
    public static boolean testConstructorArrayLength() {
        Vector vector1 = new Vector(5);

        return vector1.equals(new Vector(new double[]{0, 0, 0, 0, 0}));
    }

    public static boolean testConstructorArrayLengthException() {
        try {
            Vector vector1 = new Vector(-1);
        } catch (IllegalArgumentException e) {
            return e.getMessage().equals("Размерность вектора (n) должна быть больше нуля!");
        }

        return false;
    }

    public static boolean testConstructorCopyVector() {
        Vector vector1 = new Vector(new double[]{1, 2, 3});
        Vector vector2 = new Vector(vector1);

        return vector1.equals(vector2);
    }

    public static boolean testConstructorArrayLengthAndArray() {
        Vector vector1 = new Vector(5, new double[]{1, 2});

        return vector1.equals(new Vector(new double[]{1, 2, 0, 0, 0}));
    }


    public static boolean testAdd() {
        Vector vector1 = new Vector(new double[]{1, 2});
        Vector vector2 = new Vector(new double[]{1, 2, 3, 4});

        Vector vector = Vector.add(vector1, vector2);

        return vector.equals(new Vector(new double[]{2, 4, 3, 4}));
    }

    public static boolean testDifference() {
        Vector vector1 = new Vector(new double[]{1, 2});
        Vector vector2 = new Vector(new double[]{1, 2, 3, 4});

        Vector vector = Vector.difference(vector1, vector2);

        return vector.equals(new Vector(new double[]{0, 0, -3, -4}));
    }

    public static boolean testMultiply() {
        Vector vector1 = new Vector(new double[]{1, 2});
        Vector vector2 = new Vector(new double[]{1, 2, 3, 4});

        return Vector.multiply(vector1, vector2) == 6;
    }

    public static boolean testGetElement() {
        Vector vector = new Vector(new double[]{1, 2, 3, 4});

        return vector.getElement(2) == 3;
    }

    public static boolean testSetElement() {
        Vector vector = new Vector(new double[]{1, 2, 3, 4});
        vector.setElement(2, 1);

        return vector.equals(new Vector(new double[]{1, 2, 1, 4}));
    }

    public static boolean testGetSize() {
        Vector vector = new Vector(new double[]{1, 2, 3, 4});

        return vector.getSize() == 4;
    }

    public static boolean testAddVector() {
        Vector vector1 = new Vector(new double[]{1, 2, 3, 4});
        Vector vector2 = new Vector(new double[]{1, 2});

        vector1.addVector(vector2);

        return vector1.equals(new Vector(new double[]{2, 4, 3, 4}));
    }

    public static boolean testDifferenceVector() {
        Vector vector1 = new Vector(new double[]{1, 2, 3, 4});
        Vector vector2 = new Vector(new double[]{1, 2});

        vector1.differenceVector(vector2);

        return vector1.equals(new Vector(new double[]{0, 0, 3, 4}));
    }

    public static boolean testIncreaseByNumber() {
        Vector vector = new Vector(new double[]{1, 2, 3, 4});

        vector.increaseByNumber(5);

        return vector.equals(new Vector(new double[]{5, 10, 15, 20}));
    }

    public static boolean testGetLength() {
        Vector vector = new Vector(new double[]{1, 2, 3, 4});

        return vector.getLength() == Math.sqrt((1 + 4 + 9 + 16));
    }

    public static boolean testReverse() {
        Vector vector = new Vector(new double[]{1, 2, 3, 4});

        vector.reverse();

        return vector.equals(new Vector(new double[]{-1, -2, -3, -4}));
    }

    public static void main(String[] args) {
        System.out.println("Типо программа с проверкой различных функций");

        Vector vector1 = new Vector(4);

        System.out.println(vector1.toString());

        Vector vector2 = new Vector(new double[]{1, 1, 1, 1, 1, 1, 1, 1, 1});

        System.out.println(vector2.toString());
        System.out.printf("Длина вектора: %.2f\n", vector2.getLength());

        vector2.increaseByNumber(5.5);
        System.out.println(vector2.toString());

        vector2.reverse();
        System.out.println(vector2.toString());

        vector2.setElement(2, 10);
        System.out.println(vector2.toString());

        System.out.println(vector2.getElement(2));

        Vector vector3 = new Vector(vector2);

        vector2.addVector(vector3);
        System.out.println(vector2.toString());

        vector3.differenceVector(vector2);
        System.out.println(vector3.toString());

        System.out.println("\nПроверка работоспособности функций:\n");

        System.out.println("Конструктор n: " + testConstructorArrayLength());
        System.out.println("Конструктор n (отрицательное): " + testConstructorArrayLengthException());
        System.out.println("Копирование вектора: " + testConstructorCopyVector());
        System.out.println("Конструктор n и массив: " + testConstructorArrayLengthAndArray());
        System.out.println("Сложение векторов (стат): " + testAdd());
        System.out.println("Вычитание векторов (стат): " + testDifference());
        System.out.println("Умножение векторов (стат): " + testMultiply());
        System.out.println("Получение элемента вектора: " + testGetElement());
        System.out.println("Установка элемента вектора: " + testSetElement());
        System.out.println("Получение размерности вектора: " + testGetSize());
        System.out.println("Сложение векторов: " + testAddVector());
        System.out.println("Вычитание векторов: " + testDifferenceVector());
        System.out.println("Умножение векторан на скаляр: " + testIncreaseByNumber());
        System.out.println("Разворот вектора: " + testReverse());
        System.out.println("Получение длины вектора: " + testGetLength());
    }
}
