package ru.academits.bondyuk.singly_linked_list_main;

import ru.academits.bondyuk.singly_linked_list.SinglyLinkedList;

public class Main {
    public static void main(String[] args) {
        System.out.println("Задача List");

        SinglyLinkedList<Integer> integers = new SinglyLinkedList<>(0);

        integers.add(1);
        integers.add(2);
        integers.add(3);
        integers.add(4);
        integers.add(5);
        integers.add(6);

        System.out.println(integers);
        System.out.println(integers.getCount());

        System.out.println(integers.getElement(2).toString());

        integers.reverse();
        System.out.println(integers);
    }
}
