package ru.academits.bondyuk.array_list_main;

import ru.academits.bondyuk.array_list.ArrayList;

import java.util.*;

public class Main {
    public static boolean testAdd() {
        ArrayList<String> list = new ArrayList<>();
        return list.add("a");
    }

    public static ArrayList<String> createArrayList() {
        ArrayList<String> list = new ArrayList<>();
        list.add("a");
        list.add(null);
        list.add("b");
        list.add("c");
        list.add("d");

        return list;
    }

    public static boolean testSize() {
        return createArrayList().size() == 5;
    }

    public static boolean testToString() {
        return createArrayList().toString().equals("[a, null, b, c, d, null, null, null, null, null]");
    }

    public static boolean testAddFirst() {
        ArrayList<String> list = createArrayList();
        list.add(0, "f");

        return list.toString().equals("[f, a, null, b, c, d, null, null, null, null]");
    }

    public static boolean testAddLast() {
        ArrayList<String> list = createArrayList();
        list.add(list.size(), "f");

        return list.toString().equals("[a, null, b, c, d, f, null, null, null, null]");
    }

    public static boolean testRemoveByIndex() {
        int index = 2;
        return createArrayList().remove(index).equals("b");
    }

    public static boolean testEnsureCapacity() {
        int capacity = 15;
        ArrayList<String> list = createArrayList();
        list.ensureCapacity(capacity);

        return list.toString().equals("[a, null, b, c, d, null, null, null, null, null, null, null, null, null, null]");
    }

    public static boolean testTrimToSize() {
        ArrayList<String> list = createArrayList();
        list.trimToSize();

        return list.toString().equals("[a, null, b, c, d]");
    }

    public static boolean testToArray() {
        ArrayList<String> list = createArrayList();
        String expected = "[a, null, b, c, d]";

        return Objects.equals(Arrays.toString(list.toArray()), expected);
    }

    public static boolean testContainsContainingElement() {
        ArrayList<String> list = createArrayList();

        return list.contains("d");
    }

    public static boolean testContainNotContainingElement() {
        ArrayList<String> list = createArrayList();

        return !list.contains("f");
    }

    public static boolean testIndexOfContainingElement() {
        ArrayList<String> list = createArrayList();

        return list.indexOf("b") == 2;
    }

    public static boolean testIndexOfNotContainingElement() {
        ArrayList<String> list = createArrayList();

        return list.indexOf("f") == -1;
    }

    public static boolean testLastIndexOfContainingElement() {
        ArrayList<String> list = new ArrayList<>();
        list.add("a");
        list.add(null);
        list.add("b");
        list.add("d");
        list.add("d");

        return list.lastIndexOf("d") == 4;
    }

    public static boolean testLastIndexOfNotContainingElement() {
        ArrayList<String> list = createArrayList();

        return list.lastIndexOf("f") == -1;
    }

    public static boolean testRemoveObjectOnList() {
        ArrayList<String> list = createArrayList();
        return list.remove("b");
    }

    public static boolean testRemoveObjectOutList() {
        ArrayList<String> list = createArrayList();
        return !list.remove("f");
    }

    public static boolean testContainsAllContainingElements() {
        ArrayList<String> list = createArrayList();

        List<String> collection = new java.util.ArrayList<>();
        collection.add("d");
        collection.add("b");

        return list.containsAll(collection);
    }

    public static boolean testContainsAllNotContainingElements() {
        ArrayList<String> list = createArrayList();

        List<String> collection = new java.util.ArrayList<>();
        collection.add("d");
        collection.add("f");

        return !list.containsAll(collection);
    }

    public static boolean testAddAll() {
        ArrayList<String> list = createArrayList();

        List<String> collection = new java.util.ArrayList<>();
        collection.add("d");
        collection.add("f");

        list.addAll(collection);

        return list.toString().equals("[a, null, b, c, d, null, d, f, null, null]");
    }

    public static boolean testAddAllByIndex() {
        ArrayList<String> list = createArrayList();

        List<String> collection = new java.util.ArrayList<>();
        collection.add("f");
        collection.add("f");

        int index = 1;
        list.addAll(index, collection);

        return list.toString().equals("[a, f, f, null, b, c, d, null, null, null]");
    }

    public static boolean testRemoveAll() {
        ArrayList<String> list = createArrayList();

        List<String> collection = new java.util.ArrayList<>();
        collection.add("d");
        collection.add("f");

        list.removeAll(collection);

        return list.toString().equals("[a, null, b, c, null, null, null, null, null, null]");
    }

    public static boolean testRetainsAllOnList() {
        ArrayList<String> list = createArrayList();

        List<String> collection = new java.util.ArrayList<>();
        collection.add("c");
        collection.add("d");

        return list.retainAll(collection);
    }

    public static boolean testRetainsAllOutList() {
        ArrayList<String> list = createArrayList();

        List<String> collection = new java.util.ArrayList<>();
        collection.add("f");
        collection.add("g");

        return list.retainAll(collection);
    }

    public static boolean testClear() {
        ArrayList<String> list = createArrayList();
        list.clear();

        return list.toString().equals("[null, null, null, null, null, null, null, null, null, null]");
    }

    public static boolean testGet() {
        ArrayList<String> list = createArrayList();

        return list.get(2).equals("b");
    }

    public static boolean testSet() {
        ArrayList<String> list = createArrayList();
        list.set(2, "bb");

        return list.toString().equals("[a, null, bb, c, d, null, null, null, null, null]");
    }

    public static void runTests() {
        System.out.printf("%b : проверка add %n", testAdd());
        System.out.printf("%b : Проверка size%n", testSize());
        System.out.printf("%b : Проверка toString%n", testToString());
        System.out.printf("%b : Проверка addByIndexFirst%n", testAddFirst());
        System.out.printf("%b : Проверка addByIndexLast%n", testAddLast());
        System.out.printf("%b : Проверка remove by index%n", testRemoveByIndex());
        System.out.printf("%b : Проверка ensureCapacity%n", testEnsureCapacity());
        System.out.printf("%b : Проверка trimToSize%n", testTrimToSize());
        System.out.printf("%b : Проверка ToArray%n", testToArray());
        System.out.printf("%b : Проверка contains (содержит)%n", testContainsContainingElement());
        System.out.printf("%b : Проверка contains (не содержит)%n", testContainNotContainingElement());
        System.out.printf("%b : Проверка indexOf (содержит)%n", testIndexOfContainingElement());
        System.out.printf("%b : Проверка indexOf (не содержит)%n", testIndexOfNotContainingElement());
        System.out.printf("%b : Проверка lastIndexOf (содержит)%n", testLastIndexOfContainingElement());
        System.out.printf("%b : Проверка lastIndexOf (не содержит)%n", testLastIndexOfNotContainingElement());
        System.out.printf("%b : Проверка remove by object%n", testRemoveObjectOnList());
        System.out.printf("%b : Проверка remove by object%n", testRemoveObjectOutList());
        System.out.printf("%b : Проверка containsAll (содержит)%n", testContainsAllContainingElements());
        System.out.printf("%b : Проверка containsAll (не содержит)%n", testContainsAllNotContainingElements());
        System.out.printf("%b : Проверка addAll%n", testAddAll());
        System.out.printf("%b : Проверка addAll by index%n", testAddAllByIndex());
        System.out.printf("%b : Проверка removeAll%n", testRemoveAll());
        System.out.printf("%b : Проверка retainsAll (содержит)%n", testRetainsAllOnList());
        System.out.printf("%b : Проверка retainsAll (не содержит)%n", testRetainsAllOutList());
        System.out.printf("%b : проверка clear %n", testClear());
        System.out.printf("%b : проверка get %n", testGet());
        System.out.printf("%b : проверка set %n", testSet());
    }

    public static void main(String[] args) {
        System.out.println("Реализация ArrayList");

        runTests();
    }
}
