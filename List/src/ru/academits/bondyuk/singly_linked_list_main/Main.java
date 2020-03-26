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

        System.out.printf("Текущий список: %s%n%n", integers);
        System.out.printf("Головной элемент: %s,%nКоличество элементов: %d%n%n", integers.getFirstElement(), integers.getCount());

        int index = 2;
        System.out.printf("Получение элемента по индексу %d: %s%n%n", index, integers.getElement(index));

        System.out.printf("Текущий список: %s%n", integers);
        integers.reverse();
        System.out.printf("После разворота: %s%n%n", integers);


        int value = 3;
        integers.remove(3);
        System.out.printf("Удаление по значению %d: %s%n", value, integers);
        System.out.printf("Головной элемент: %s,%nКоличество элементов: %d%n%n", integers.getFirstElement(), integers.getCount());

        integers.addElementToStart(value);
        System.out.printf("Добавление в начало значения %d: %s%n", value, integers);
        System.out.printf("Головной элемент: %s,%nКоличество элементов: %d%n%n", integers.getFirstElement(), integers.getCount());

        int addedValue = -1;
        int i = 5;
        integers.add(i, addedValue);
        System.out.printf("Добавление по индексу %d значения %d: %s%n", i, addedValue, integers);
        System.out.printf("Головной элемент: %s,%nКоличество элементов: %d%n%n", integers.getFirstElement(), integers.getCount());

        Integer deletedValue1 = 15;
        System.out.printf("Удаление по значению %d: %b%n", deletedValue1, integers.remove(deletedValue1));
        System.out.printf("Головной элемент: %s,%nКоличество элементов: %d%n%n", integers.getFirstElement(), integers.getCount());

        Integer deletedValue2 = 2;
        System.out.printf("Удаление по значению %d: %b%n", deletedValue2, integers.remove(deletedValue2));
        System.out.printf("Текущий список: %s%n", integers);
        System.out.printf("Головной элемент: %s,%nКоличество элементов: %d%n%n", integers.getFirstElement(), integers.getCount());

        int deletedValue3 = 1;
        System.out.printf("Удаление по индексу %d, старое значение %d%n", deletedValue3, integers.remove(deletedValue3));
        System.out.printf("Текущий список: %s%n", integers);
        System.out.printf("Головной элемент: %s,%nКоличество элементов: %d%n%n", integers.getFirstElement(), integers.getCount());

        System.out.printf("Удаление первого элемента, старое значение %s%n", integers.removeElementFromStart());
        System.out.printf("Текущий список: %s%n", integers);
        System.out.printf("Головной элемент: %s,%nКоличество элементов: %d%n%n", integers.getFirstElement(), integers.getCount());

        int insertedIndex = 2;
        int insertedValue = 99;
        System.out.printf("Вставка элемента %s, по индексу %d. Старое значение %s%n", insertedValue, insertedIndex, integers.setElement(insertedIndex, insertedValue));
        System.out.printf("Текущий список: %s%n", integers);
        System.out.printf("Головной элемент: %s,%nКоличество элементов: %d%n%n", integers.getFirstElement(), integers.getCount());


        System.out.printf("Текущий список: %s%n", integers);
        SinglyLinkedList<Integer> integers2 = integers.copy();

        int index2 = 2;
        integers.add(index2, addedValue);
        System.out.printf("Добавление по индексу %d значения %d: %s%n", index2, addedValue, integers);
        System.out.printf("Обновленный список: %s%n", integers);
        System.out.printf("Скопированный список: %s%n", integers2);
    }
}