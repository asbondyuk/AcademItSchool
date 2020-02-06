package ru.academits.bondyuk.vector;

public class Main {
    public static void main(String[] args) {
        Vector vector1 = new Vector(4);

        System.out.println(vector1.toString());

        Vector vector2 = new Vector(new double[]{1, 1, 1, 1, 1, 1, 1, 1, 1});

        System.out.println(vector2.toString());
        System.out.printf("Длина вектора: %.2f\n", vector2.getLength());

        vector2.increaseByNumber(5.5);
        System.out.println(vector2.toString());

        vector2.reverse();
        System.out.println(vector2.toString());

        vector2.setElement(2,10);
        System.out.println(vector2.toString());

        System.out.println(vector2.getElement(2));

        Vector vector3 = new Vector(vector2);

        vector2.addVector(vector3);
        System.out.println(vector2.toString());

        vector3.differenceVector(vector2);
        System.out.println(vector3.toString());
    }
}
