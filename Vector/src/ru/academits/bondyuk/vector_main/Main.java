package ru.academits.bondyuk.vector_main;

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
            return true;
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


    public static boolean testStaticAdd() {
        Vector vector1 = new Vector(new double[]{1, 2});
        Vector vector2 = new Vector(new double[]{1, 2, 3, 4});

        Vector vector = Vector.getSum(vector1, vector2);

        return vector.equals(new Vector(new double[]{2, 4, 3, 4}));
    }

    public static boolean testStaticDifference() {
        Vector vector1 = new Vector(new double[]{1, 2});
        Vector vector2 = new Vector(new double[]{1, 2, 3, 4});

        Vector vector = Vector.getDifference(vector1, vector2);

        return vector.equals(new Vector(new double[]{0, 0, -3, -4}));
    }

    public static boolean testStaticMultiply() {
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

    public static boolean testAdd() {
        Vector vector1 = new Vector(new double[]{1, 2, 3, 4});
        Vector vector2 = new Vector(new double[]{1, 2});

        vector1.add(vector2);

        return vector1.equals(new Vector(new double[]{2, 4, 3, 4}));
    }

    public static boolean testDifference() {
        Vector vector1 = new Vector(new double[]{1, 2, 3, 4});
        Vector vector2 = new Vector(new double[]{1, 2});

        vector1.subtract(vector2);

        return vector1.equals(new Vector(new double[]{0, 0, 3, 4}));
    }

    public static boolean testMultiplyByNumber() {
        Vector vector = new Vector(new double[]{1, 2, 3, 4});

        vector.multiply(5);

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

        vector2.multiply(5.5);
        System.out.println(vector2.toString());

        vector2.reverse();
        System.out.println(vector2.toString());

        vector2.setElement(2, 10);
        System.out.println(vector2.toString());

        System.out.println(vector2.getElement(2));

        Vector vector3 = new Vector(vector2);

        vector2.add(vector3);
        System.out.println(vector2.toString());

        vector3.subtract(vector2);
        System.out.println(vector3.toString());

        System.out.printf("%nПроверка работоспособности функций:%n");

        System.out.printf("%b : Конструктор n%n", testConstructorArrayLength());
        System.out.printf("%b : Конструктор n (отрицательное)%n", testConstructorArrayLengthException());
        System.out.printf("%b : Копирование вектора%n", testConstructorCopyVector());
        System.out.printf("%b : Конструктор n и массив%n", testConstructorArrayLengthAndArray());
        System.out.printf("%b : Сложение векторов (стат)%n", testStaticAdd());
        System.out.printf("%b : Вычитание векторов (стат)%n", testStaticDifference());
        System.out.printf("%b : Умножение векторов (стат)%n", testStaticMultiply());
        System.out.printf("%b : Получение элемента вектора%n", testGetElement());
        System.out.printf("%b : Установка элемента вектора%n", testSetElement());
        System.out.printf("%b : Получение размерности вектора%n", testGetSize());
        System.out.printf("%b : Сложение векторов%n", testAdd());
        System.out.printf("%b : Вычитание векторов%n", testDifference());
        System.out.printf("%b : Умножение векторан на скаляр%n", testMultiplyByNumber());
        System.out.printf("%b : Разворот вектора%n", testReverse());
        System.out.printf("%b : Получение длины вектора%n", testGetLength());
    }
}