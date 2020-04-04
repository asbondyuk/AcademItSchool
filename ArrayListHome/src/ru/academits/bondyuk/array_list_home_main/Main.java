package ru.academits.bondyuk.array_list_home_main;

import ru.academits.bondyuk.array_list_home.ArrayListHome;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        System.out.println("Задача ArrayListHome");

        // part 1
        String filePath = "input.txt";

        List<String> fileLines = ArrayListHome.getFileLines(filePath);
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
        List<Integer> duplicatedNumbersList = new ArrayList<>();

        duplicatedNumbersList.add(1);
        duplicatedNumbersList.add(5);
        duplicatedNumbersList.add(2);
        duplicatedNumbersList.add(1);
        duplicatedNumbersList.add(3);
        duplicatedNumbersList.add(5);

        System.out.printf("Дублированный список: %s%n", duplicatedNumbersList);

        Set<Integer> numberSet = new LinkedHashSet<>(duplicatedNumbersList);
        duplicatedNumbersList.clear();
        duplicatedNumbersList.addAll(numberSet);

        System.out.printf("Очищенный от дублей список: %s%n", duplicatedNumbersList);

        // part 3 version 2
        duplicatedNumbersList.clear();
        duplicatedNumbersList = new ArrayList<>();

        duplicatedNumbersList.add(1);
        duplicatedNumbersList.add(5);
        duplicatedNumbersList.add(2);
        duplicatedNumbersList.add(1);
        duplicatedNumbersList.add(3);
        duplicatedNumbersList.add(5);

        System.out.printf("Дублированный список: %s%n", duplicatedNumbersList);

        List<Integer> withoutDuplicatedNumbersList = new ArrayList<>();

        for (Integer number : duplicatedNumbersList) {
            if (!withoutDuplicatedNumbersList.contains(number)) {
                withoutDuplicatedNumbersList.add(number);
            }
        }

        System.out.printf("Очищенный от дублей список: %s%n", withoutDuplicatedNumbersList);
    }
}