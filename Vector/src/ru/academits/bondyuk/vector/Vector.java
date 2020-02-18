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
        if (n <= 0) {
            throw new IllegalArgumentException("Размерность вектора (n) должна быть больше нуля!");
        }

        components = Arrays.copyOf(array, n);
    }

    public static Vector add(Vector vector1, Vector vector2) {
        int vector1Length = vector1.getComponents().length;
        int vector2Length = vector2.getComponents().length;
        int newComponentsLength = Math.max(vector1Length, vector2Length);
        double[] newComponents = new double[newComponentsLength];

        for (int i = 0; i < newComponentsLength; ++i) {
            double firstTerm = 0;
            if (i < vector1Length) {
                firstTerm = vector1.getElement(i);
            }

            double secondTerm = 0;
            if (i < vector2Length) {
                secondTerm = vector2.getElement(i);
            }

            newComponents[i] = firstTerm + secondTerm;
        }

        return new Vector(newComponents);
    }

    public static Vector difference(Vector vector1, Vector vector2) {
        int vector1Length = vector1.getComponents().length;
        int vector2Length = vector2.getComponents().length;
        int newComponentsLength = Math.max(vector1Length, vector2Length);
        double[] newComponents = new double[newComponentsLength];

        for (int i = 0; i < newComponentsLength; ++i) {
            double firstTerm = 0;
            if (i < vector1Length) {
                firstTerm = vector1.getElement(i);
            }

            double secondTerm = 0;
            if (i < vector2Length) {
                secondTerm = vector2.getElement(i);
            }

            newComponents[i] = firstTerm - secondTerm;
        }

        return new Vector(newComponents);
    }

    public static double multiply(Vector vector1, Vector vector2) {
        int baseLength = Math.min(vector1.getComponents().length, vector2.getComponents().length);
        double multiplicationResult = 0;

        for (int i = 0; i < baseLength; ++i) {
            multiplicationResult += vector1.getElement(i) + vector2.getElement(i);
        }

        return multiplicationResult;
    }

    public double[] getComponents() {
        return components;
    }

    public void setComponents(double[] components) {
        this.components = components;
    }

    public int getSize() {
        return components.length;
    }

    public void add(Vector vector) {
        int newComponentsLength = Math.max(components.length, vector.getComponents().length);
        double[] newComponents = new double[newComponentsLength];

        for (int i = 0; i < newComponentsLength; ++i) {
            double firstTerm = 0;
            if (i < components.length) {
                firstTerm = components[i];
            }

            double secondTerm = 0;
            if (i < vector.getComponents().length) {
                secondTerm = vector.getComponents()[i];
            }

            newComponents[i] = firstTerm + secondTerm;
        }

        setComponents(newComponents);
    }

    public void difference(Vector vector) {
        int addedVectorLength = vector.getComponents().length;
        int newComponentsLength = Math.max(components.length, addedVectorLength);
        double[] newComponents = new double[newComponentsLength];

        for (int i = 0; i < newComponentsLength; ++i) {
            double firstTerm = 0;
            if (i < components.length) {
                firstTerm = components[i];
            }

            double secondTerm = 0;
            if (i < addedVectorLength) {
                secondTerm = vector.getElement(i);
            }

            newComponents[i] = firstTerm - secondTerm;
        }

        setComponents(newComponents);
    }

    public void multiplyByNumber(double number) {
        double[] newComponents = new double[components.length];

        for (int i = 0; i < components.length; ++i) {
            newComponents[i] = components[i] * number;
        }

        setComponents(newComponents);
    }

    public void reverse() {
        multiplyByNumber(-1);
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

        return Arrays.equals(components, vector.components) && (components.length == vector.getComponents().length);
    }

    @Override
    public int hashCode() {
        int componentsHash = Arrays.hashCode(components);

        return 31 * componentsHash + components.length;
    }
}
