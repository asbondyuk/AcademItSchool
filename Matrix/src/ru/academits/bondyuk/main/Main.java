package ru.academits.bondyuk.main;

import ru.academits.bondyuk.matrix.Matrix;
import ru.academits.bondyuk.vector.Vector;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        int n = 2;
        int m = 3;
        Matrix matrix1 = new Matrix(n, m);
        System.out.println(matrix1);

//        Matrix matrix2 = new Matrix(new double[][]{{1, 1, 1}, {2, 2, 2}});
//        System.out.println(matrix2);
//
//        Matrix matrix3 = new Matrix(matrix2);
//        System.out.println(matrix3);

        Vector[] vectors = new Vector[]{new Vector(new double[]{3, 3, 3, 3}), new Vector(new double[]{4, 4, 4, 4})};
        Matrix matrix4 = new Matrix(vectors);
        System.out.println(Arrays.toString(matrix4.getVectors()));

        System.out.println(Arrays.toString(matrix4.getSize()));

        int index = 1;
        System.out.println(matrix4.getVector(index));

        Vector vector = new Vector(new double[]{5, 5, 5});

        System.out.println(matrix4);
        matrix4.setVector(vector, index);
        System.out.println(matrix4);
    }
}