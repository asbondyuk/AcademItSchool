package ru.academits.bondyuk.vector;

import java.util.Arrays;

public class Vector {
    private double[] components;

    public Vector(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("Размерность вектора (n) должна быть больше нуля!");
        }

        components = new double[n];
    }

    public Vector(Vector vector) {
        components = vector.getComponents();
    }

    public Vector(double[] array) {
        components = Arrays.copyOf(array, array.length);
    }

    public Vector(int n, double[] array) {
        components = Arrays.copyOf(array, n);
    }

    public double[] getComponents() {
        return components;
    }

    public int getSize() {
        return components.length;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("{");

        for (int i = 0; i < components.length; ++i) {
            stringBuilder.append(components[i]);

            if (i + 1 != components.length) {
                stringBuilder.append(", ");
            }
        }

        stringBuilder.append("}");

        return stringBuilder.toString();
    }
}
