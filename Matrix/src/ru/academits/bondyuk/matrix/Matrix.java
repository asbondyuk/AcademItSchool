package ru.academits.bondyuk.matrix;

import ru.academits.bondyuk.vector.Vector;

// TODO добавить выравнивание размерности векторов (max Vector размер, добивание 0 до max размера др векторов, при потребности
public class Matrix {
    private Vector[] vectors;

    public Matrix(int n, int m) {
        vectors = new Vector[m];

        for (int i = 0; i < m; ++i) {
            vectors[i] = new Vector(n);
        }
    }

    public Matrix(Matrix matrix) {
        int m = matrix.getVectors().length;
        vectors = new Vector[m];

        for (int i = 0; i < m; ++i) {
            vectors[i] = new Vector(matrix.getVectors()[i]);
        }
    }

    public Matrix(double[][] arrays) {
        int m = arrays.length;
        vectors = new Vector[m];

        for (int i = 0; i < m; ++i) {
            vectors[i] = new Vector(arrays[i]);
        }
    }

    public Matrix(Vector[] vectors) {
        int m = vectors.length;
        this.vectors = new Vector[m];

        System.arraycopy(vectors, 0, this.vectors, 0, m);
    }

    public Vector[] getVectors() {
        return vectors;
    }

    public int[] getSize() {
        int m = vectors.length;
        int n = vectors[0].getSize();

        return new int[]{n, m};
    }

    public Vector getVector(int index) {
        return vectors[index];
    }

    public void setVector(Vector vector, int index) {
        vectors[index] = vector;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("{");

        for (int i = 0; i < vectors.length; ++i) {
            stringBuilder.append(vectors[i].toString());

            if (i + 1 != vectors.length) {
                stringBuilder.append(", ");
            }
        }

        stringBuilder.append("}");

        return stringBuilder.toString();
    }
}