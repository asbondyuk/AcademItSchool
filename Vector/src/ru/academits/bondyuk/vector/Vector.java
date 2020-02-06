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

    public void setComponents(double[] components) {
        this.components = components;
    }

    public int getSize() {
        return components.length;
    }

    public void addVector(Vector vector) {
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


    public void differenceVector(Vector vector) {
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

            newComponents[i] = firstTerm - secondTerm;
        }

        setComponents(newComponents);
    }

    public void increaseByNumber(double number) {
        double[] newComponents = new double[components.length];

        for (int i = 0; i < components.length; ++i) {
            newComponents[i] = components[i] * number;
        }

        setComponents(newComponents);
    }

    public void reverse() {
        increaseByNumber(-1);
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
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vector vector = (Vector) o;

        return Arrays.equals(components, vector.components) && (components.length == vector.getComponents().length);
    }

    @Override
    public int hashCode() {
        int componentsHash = Arrays.hashCode(components);

        return 31 * componentsHash + components.length;
    }
}
