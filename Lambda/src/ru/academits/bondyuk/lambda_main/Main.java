package ru.academits.bondyuk.lambda_main;

import ru.academits.bondyuk.person.Person;

import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        System.out.println("Задача Лямбды");

        List<Person> personList = new ArrayList<>();

        personList.add(new Person("Петр", 5));
        personList.add(new Person("Петр", 15));
        personList.add(new Person("Иван", 18));
        personList.add(new Person("Сергей", 25));
        personList.add(new Person("Сергей", 26));
        personList.add(new Person("Иван", 33));
        personList.add(new Person("Петр", 44));
        personList.add(new Person("Алексей", 60));

        System.out.printf("Общий список: %s%n%n", personList);

        // А - получение списока уникальных имен
        List<String> uniqueNamesList = personList.stream()
                .map(Person::getName)
                .distinct()
                .collect(Collectors.toList());

        System.out.printf("Список уникальных имен: %s%n%n", uniqueNamesList);

        // Б - вывод списока уникальных имен
        String uniqueNames = personList.stream()
                .map(Person::getName)
                .distinct()
                .collect(Collectors.joining(", "));

        System.out.printf("Список уникальных имен: %s%n%n", uniqueNames);

        // В - получение списока людей младше 18, подсчет среднего возраста
        List<Person> less18AgeList = personList.stream()
                .filter(person -> person.getAge() < 18)
                .collect(Collectors.toList());

        System.out.printf("Список лиц до 18 лет: %s%n", less18AgeList);

        OptionalDouble less18AverageAge = personList.stream()
                .filter(person -> person.getAge() < 18)
                .mapToDouble(Person::getAge)
                .average();

        if (less18AverageAge.isPresent()) {
            System.out.printf("Средний возраст лиц до 18 лет: %s%n%n", less18AverageAge.getAsDouble());
        }

        // Г -при помощи группировки получение Map, в котором ключи – имена, а значения – средний возраст
        Map<String, Double> averageByNamesAge = personList.stream()
                .collect(Collectors.groupingBy(Person::getName, Collectors.averagingDouble(Person::getAge)));

        System.out.printf("Средний возраст по именам: %s%n%n", averageByNamesAge);

        // Д - получение имен людей в порядке убывания возраста, возраст которых от 20 до 45
        List<String> sortedByAgeNames = personList.stream()
                .filter(person -> person.getAge() >= 20 && person.getAge() <= 45)
                .sorted(Comparator.comparingInt(Person::getAge).reversed())
                .map(Person::getName)
                .collect(Collectors.toList());

        System.out.printf("Имена лиц от 20 до 45 лет в порядке убывания возраста: %s%n%n", sortedByAgeNames);

        List<Person> sortedByAgeNameChecker = personList.stream()
                .filter(person -> person.getAge() >= 20 && person.getAge() <= 45)
                .sorted(Comparator.comparingInt(Person::getAge).reversed())
                .collect(Collectors.toList());

        System.out.printf("Персоны от 20 до 45: %s", sortedByAgeNameChecker);
    }
}