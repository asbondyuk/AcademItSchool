package ru.academits.bondyuk.matrix_main;

import ru.academits.bondyuk.matrix.Matrix;
import ru.academits.bondyuk.vector.Vector;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        int n = 2;
        int m = 3;
        Matrix matrix1 = new Matrix(n, m);
        System.out.printf("Пустая матрица размера %dx%d: %s%n%n", n, m, matrix1);

        Vector row1 = new Vector(new double[]{3, 3, 3, 3});
        Vector row2 = new Vector(new double[]{4, 4, 4, 4});

        Vector[] rows = new Vector[]{row1, row2};
        Matrix matrix4 = new Matrix(rows);
        System.out.printf("Матрица из векторов %s и %s: %s%n", row1, row2, matrix4);
        System.out.printf("Размерность вектора: (%d, %d)%n", matrix4.getRowsCount(), matrix4.getColumnsCount());

        int index = 1;
        System.out.printf("Получение вектора матрицы по индексу %d: %s%n%n", index, matrix4.getRow(index));

        Vector row3 = new Vector(new double[]{5, 6, 7, 8});

        System.out.printf("Проверим операции.%nНапомню текущее состояние вектора: %s%n", matrix4);
        System.out.printf("Заменим %d элемент вектором %s%n", index, row3);
        matrix4.setRow(row3, index);
        System.out.printf("Проверяем замену: %s%n", matrix4);

        int columnIndex = 2;
        System.out.printf("Выведем %d колонку %s%n%n", columnIndex, matrix4.getColumn(columnIndex));

        System.out.printf("Напомню текущее состояние вектора: %s%n", matrix4);
        matrix4.transpose();
        System.out.printf("Вектор после траспонирования: %s%n", matrix4);

        int number = 2;
        matrix4.multiply(number);
        System.out.printf("Умножаем на число %d: %s%n", number, matrix4);

        Vector row4 = new Vector(new double[]{10, 0.5});
        System.out.printf("Умножаем на вектор %s: %s%n%n", row4, matrix4.multiply(row4));

        Matrix matrix5 = new Matrix(new Vector[]{new Vector(new double[]{3, 3, 3}), new Vector(new double[]{4, 4, 4})});
        Matrix matrix6 = new Matrix(new Vector[]{new Vector(new double[]{3, 3, 3}), new Vector(new double[]{4, 4, 4})});

        System.out.printf("Проверка статического сложения векторов %s и %s: %s%n", matrix5, matrix6, Matrix.add(matrix5, matrix6));
        System.out.printf("Проверка статического вычитания векторов %s и %s: %s%n", matrix5, matrix6, Matrix.subtract(matrix5, matrix6));

        System.out.printf("Новые вектора: %s и %s%n", matrix5, matrix6);
        matrix5.add(matrix6);
        System.out.printf("Результат сложения 1-го и 2-го: %s%n", matrix5);

        matrix6.subtract(matrix5);
        System.out.printf("Результат вычитания из 2-го суммы (1 и 2): %s%n", matrix6);

        Matrix matrix7 = new Matrix(new Vector[]{new Vector(new double[]{3, 3}), new Vector(new double[]{4, 4})});
        Matrix matrix8 = new Matrix(new Vector[]{new Vector(new double[]{6, 8, 10}), new Vector(new double[]{6, 8, 10})});
        Matrix multiplyExpectedMatrix = new Matrix(new Vector[]{new Vector(new double[]{36, 48, 60}), new Vector(new double[]{48, 64, 80})});
        System.out.println(matrix7);
        System.out.println(matrix8);

        System.out.printf("Результат перемножения: %s%n", Matrix.multiply(matrix7, matrix8));
        System.out.printf("Ожидаемый результат перемножения: %s%n", multiplyExpectedMatrix);
    }
}