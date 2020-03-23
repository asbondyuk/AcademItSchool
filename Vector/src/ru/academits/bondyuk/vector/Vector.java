package ru.academits.bondyuk.vector;

import java.util.Arrays;

public class Vector {
    private double[] components;

    public Vector(int vectorDimension) {
        if (vectorDimension <= 0) {
            throw new IllegalArgumentException("Размерность вектора должна быть больше нуля!");
        }

        components = new double[vectorDimension];
    }

    public Vector(Vector vector) {
        components = Arrays.copyOf(vector.components, vector.components.length);
    }

    public Vector(double[] array) {
        if (array.length <= 0) {
            throw new IllegalArgumentException("Размерность вектора должна быть больше нуля!");
        }

        components = Arrays.copyOf(array, array.length);
    }

    public Vector(int vectorDimension, double[] array) {
        if (vectorDimension <= 0) {
            throw new IllegalArgumentException("Размерность вектора должна быть больше нуля!");
        }

        components = Arrays.copyOf(array, vectorDimension);
    }

    public static Vector getSum(Vector vector1, Vector vector2) {
        Vector vector = new Vector(vector1);

        vector.add(vector2);

        return vector;
    }

    public static Vector getDifference(Vector vector1, Vector vector2) {
        Vector vector = new Vector(vector1);

        vector.subtract(vector2);

        return vector;
    }

    public static double multiply(Vector vector1, Vector vector2) {
        int baseLength = Math.min(vector1.getSize(), vector2.getSize());
        double multiplicationResult = 0;

        for (int i = 0; i < baseLength; ++i) {
            multiplicationResult += vector1.components[i] * vector2.components[i];
        }

        return multiplicationResult;
    }

    public int getSize() {
        return components.length;
    }

    public void add(Vector vector) {
        if (components.length < vector.getSize()) {
            components = Arrays.copyOf(components, vector.getSize());
        }

        for (int i = 0; i < vector.getSize(); ++i) {
            components[i] += vector.components[i];
        }
    }

    public void subtract(Vector vector) {
        if (components.length < vector.getSize()) {
            components = Arrays.copyOf(components, vector.getSize());
        }

        for (int i = 0; i < vector.getSize(); ++i) {
            components[i] -= vector.components[i];
        }
    }

    public void multiply(double number) {
        for (int i = 0; i < components.length; ++i) {
            components[i] *= number;
        }
    }

    public void reverse() {
        multiply(-1);
    }

    public double getLength() {
        double componentsSquaredSum = 0;

        for (double component : components) {
            componentsSquaredSum += Math.pow(component, 2);
        }

        return Math.sqrt(componentsSquaredSum);
    }

    public double getElement(int index) {
        return components[index];
    }

    public void setElement(int index, double number) {
        components[index] = number;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Vector vector = (Vector) o;

        return Arrays.equals(components, vector.components);
    }

    @Override
    public int hashCode() {
        int componentsHash = Arrays.hashCode(components);

        return 31 * componentsHash;
    }
}
