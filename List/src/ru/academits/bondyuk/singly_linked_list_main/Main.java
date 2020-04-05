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
//        integers.add(null);

        System.out.printf("Текущий список: %s%n%n", integers);
        System.out.printf("Головной элемент: %s,%nКоличество элементов: %d%n%n", integers.getFirst(), integers.getCount());

        int index = 2;
        System.out.printf("Получение элемента по индексу %d: %s%n%n", index, integers.getElement(index));

        System.out.printf("Текущий список: %s%n", integers);
        integers.reverse();
        System.out.printf("После разворота: %s%n%n", integers);


        int data = 3;
        integers.remove(3);
        System.out.printf("Удаление по значению %d: %s%n", data, integers);
        System.out.printf("Головной элемент: %s,%nКоличество элементов: %d%n%n", integers.getFirst(), integers.getCount());

        integers.addElementToStart(data);
        System.out.printf("Добавление в начало значения %d: %s%n", data, integers);
        System.out.printf("Головной элемент: %s,%nКоличество элементов: %d%n%n", integers.getFirst(), integers.getCount());

        int addedData = -1;
        int i = 5;
        integers.add(i, addedData);
        System.out.printf("Добавление по индексу %d значения %d: %s%n", i, addedData, integers);
        System.out.printf("Головной элемент: %s,%nКоличество элементов: %d%n%n", integers.getFirst(), integers.getCount());

        Integer deletedData1 = 15;
        System.out.printf("Удаление по значению %d: %b%n", deletedData1, integers.remove(deletedData1));
        System.out.printf("Головной элемент: %s,%nКоличество элементов: %d%n%n", integers.getFirst(), integers.getCount());

        Integer deletedData2 = 2;
        System.out.printf("Удаление по значению %d: %b%n", deletedData2, integers.remove(deletedData2));
        System.out.printf("Текущий список: %s%n", integers);
        System.out.printf("Головной элемент: %s,%nКоличество элементов: %d%n%n", integers.getFirst(), integers.getCount());

        int deletedDataIndex = 1;
        System.out.printf("Удаление по индексу %d, старое значение %d%n", deletedDataIndex, integers.remove(deletedDataIndex));
        System.out.printf("Текущий список: %s%n", integers);
        System.out.printf("Головной элемент: %s,%nКоличество элементов: %d%n%n", integers.getFirst(), integers.getCount());

        System.out.printf("Удаление первого элемента, старое значение %s%n", integers.removeFirst());
        System.out.printf("Текущий список: %s%n", integers);
        System.out.printf("Головной элемент: %s,%nКоличество элементов: %d%n%n", integers.getFirst(), integers.getCount());

        int setDataIndex = 2;
        int setData = 99;
        System.out.printf("Установка элемента со значением %s, по индексу %d. Старое значение %s%n", setData, setDataIndex, integers.setElement(setDataIndex, setData));
        System.out.printf("Текущий список: %s%n", integers);
        System.out.printf("Головной элемент: %s,%nКоличество элементов: %d%n%n", integers.getFirst(), integers.getCount());


        System.out.printf("Текущий список: %s%n", integers);
        SinglyLinkedList<Integer> integers2 = integers.copy();

        int index2 = 2;
        integers.add(index2, addedData);
        System.out.printf("Добавление по индексу %d значения %d: %s%n", index2, addedData, integers);
        System.out.printf("Обновленный список: %s%n", integers);
        System.out.printf("Скопированный список: %s%n", integers2);

        integers.add(null);
        integers.add(6,13);
        System.out.printf("Текущий список: %s%n", integers);

        System.out.println(integers.getElement(5));
        System.out.println(integers.getElement(6));

//        integers.remove(null);
//        System.out.printf("Текущий список: %s%n", integers);

    }
}