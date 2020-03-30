package ru.academits.bondyuk.array_list_home;

import ru.academits.bondyuk.array_list_home_main.ArrayListHome;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        System.out.println("Задача ArrayListHome");

        // part 1
        String fileName = "input.txt";

        List<String> fileLines = ArrayListHome.getFileLines(fileName);
        System.out.println("Список из файла: " + fileLines);

        // part 2
        List<Integer> numbersList = new ArrayList<>();

        numbersList.add(1);
        numbersList.add(2);
        numbersList.add(3);
        numbersList.add(4);
        numbersList.add(5);
        numbersList.add(6);

        System.out.printf("Первоначальный список: %s%n", numbersList);

        for (int i = 0; i < numbersList.size(); ++i) {
            if (numbersList.get(i) % 2 == 0) {
                numbersList.remove(i);
                --i;
            }
        }

        System.out.printf("Список, после удаления четных элементов: %s%n", numbersList);

        // part 3 version 1
        List<Integer> duplicatedNumberList = new ArrayList<>();

        duplicatedNumberList.add(1);
        duplicatedNumberList.add(5);
        duplicatedNumberList.add(2);
        duplicatedNumberList.add(1);
        duplicatedNumberList.add(3);
        duplicatedNumberList.add(5);

        System.out.printf("Дублированный список: %s%n", duplicatedNumberList);

        Set<Integer> numberSet = new LinkedHashSet<>(duplicatedNumberList);
        duplicatedNumberList.clear();
        duplicatedNumberList.addAll(numberSet);

        System.out.printf("Очищенный от дублей список: %s%n", duplicatedNumberList);

        // part 3 version 2
        duplicatedNumberList.clear();
        duplicatedNumberList = new ArrayList<>();

        duplicatedNumberList.add(1);
        duplicatedNumberList.add(5);
        duplicatedNumberList.add(2);
        duplicatedNumberList.add(1);
        duplicatedNumberList.add(3);
        duplicatedNumberList.add(5);

        System.out.printf("Дублированный список: %s%n", duplicatedNumberList);

        List<Integer> withoutDuplicatedNumberList = new ArrayList<>();

        for (Integer i : duplicatedNumberList) {
            if (!withoutDuplicatedNumberList.contains(i)) {
                withoutDuplicatedNumberList.add(i);
            }
        }

        System.out.printf("Очищенный от дублей список: %s%n", withoutDuplicatedNumberList);
    }
}