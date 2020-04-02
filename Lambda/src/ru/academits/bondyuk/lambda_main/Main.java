package ru.academits.bondyuk.lambda_main;

import ru.academits.bondyuk.person.Person;

import java.util.ArrayList;
import java.util.List;
import java.util.OptionalDouble;
import java.util.stream.Collectors;

/**
 * • Г) при помощи группировки получить Map, в котором ключи –
 * имена, а значения – средний возраст
 * • Д) получить людей, возраст которых от 20 до 45, вывести в консоль
 * их имена в порядке убывания возраста
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("Задача Лямбды");

        List<Person> personList = new ArrayList<>();

        personList.add(new Person("Петр", 5));
        personList.add(new Person("Петр", 15));
        personList.add(new Person("Иван", 18));
        personList.add(new Person("Сергей", 25));
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

        List<Person> less18List = personList.stream()
                .filter(person -> person.getAge() < 18)
                .collect(Collectors.toList());

        System.out.printf("Список лиц до 18 лет: %s%n", less18List);

        OptionalDouble less18AverageAge = personList.stream()
                .filter(person -> person.getAge() < 18)
                .mapToDouble(Person::getAge)
                .average();

        if (less18AverageAge.isPresent()) {
            System.out.printf("Средний возраст лиц до 18 лет: %s%n%n", less18AverageAge.getAsDouble());
        }


    }
}
