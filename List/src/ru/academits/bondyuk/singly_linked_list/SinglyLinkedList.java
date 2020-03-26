package ru.academits.bondyuk.singly_linked_list;

import ru.academits.bondyuk.list_item.ListItem;

public class SinglyLinkedList<T> {
    private ListItem<T> head;
    private int count;

    public SinglyLinkedList(T value) {
        head = new ListItem<>(value);
        count = 1;
    }

    public ListItem<T> getHead() {
        return head;
    }

    public void setHead(ListItem<T> head) {
        this.head = head;
    }

    public int getCount() {
        return count;
    }

    public ListItem<T> getFirstElement() {
        return head;
    }

    public void addElementToStart(T value) {
        head = new ListItem<>(value, head);
        ++count;
    }

    public ListItem<T> removeElementFromStart() {
        ListItem<T> deletedItem = head;

        head = head.getNext();
        --count;

        return deletedItem;
    }

    public ListItem<T> getElement(int index) {
        if (!verifyArrayIndex(index)) {
            throw new ArrayIndexOutOfBoundsException("Некорретный индекс массива, должно быть значение от 0 до " + (count - 1)
                    + ". Получен: " + index);
        }

        ListItem<T> item = head;

        for (int i = 0; i < index; ++i) {
            item = item.getNext();
        }

        return item;
    }

    public T setElement(int index, T value) {
        if (!verifyArrayIndex(index)) {
            throw new ArrayIndexOutOfBoundsException("Некорретный индекс массива, должно быть значение от 0 до " + (count - 1)
                    + ".%nПолучен: " + index);
        }

        ListItem<T> element = getElement(index);
        T oldValue = element.getData();
        element.setData(value);

        return oldValue;
    }

    public T remove(int index) {
        if (!verifyArrayIndex(index)) {
            throw new ArrayIndexOutOfBoundsException("Некорретный индекс массива, должно быть значение от 0 до " + (count - 1)
                    + ".%nПолучен: " + index);
        }

        if (index == 0) {
            removeElementFromStart();
        }

        ListItem<T> previousItem = getElement(index - 1);
        ListItem<T> deletedItem = previousItem.getNext();

        if (deletedItem.hasNext()) {
            ListItem<T> nextItem = deletedItem.getNext();
            previousItem.setNext(nextItem);
        } else {
            previousItem.setNext(null);
        }

        --count;

        return deletedItem.getData();
    }

    public void add(int index, T value) {
        if (!verifyArrayIndex(index)) {
            throw new ArrayIndexOutOfBoundsException("Некорретный индекс массива, должно быть значение от 0 до " + (count - 1)
                    + ".%nПолучен: " + index);
        }

        ListItem<T> currentItem = getElement(index);

        if (currentItem.hasNext()) {
            ListItem<T> nextItem = currentItem.getNext();
            ListItem<T> newItem = new ListItem<>(value, nextItem);
            currentItem.setNext(newItem);
        } else {
            ListItem<T> newItem = new ListItem<>(value);
            currentItem.setNext(newItem);
        }

        ++count;
    }

    public boolean remove(T value) {
        int index = 0;

        for (ListItem<T> item = head; item != null; item = item.getNext()) {
            if (item.getData().equals(value)) {
                remove(index);
                return true;
            } else {
                ++index;
            }
        }

        return false;
    }

    public void reverse() {
        if (count == 1) {
            return;
        }

        ListItem<T> currentItem = head; // 1
        ListItem<T> previousItem = null;

        previousItem = currentItem; // 1
        currentItem = currentItem.getNext(); // 2

        previousItem.setNext(null); // разворачиваем next

        ListItem<T> tmp;

        while (currentItem.hasNext()) {
            tmp = previousItem; //2
            previousItem = currentItem; // 2
            currentItem = currentItem.getNext(); // 3
            previousItem.setNext(tmp); //
        }

        currentItem.setNext(previousItem);
        head = currentItem;
    }

    public void add(T value) {
        ListItem<T> item = head;

        while (item.hasNext()) {
            item = item.getNext();
        }

        ListItem<T> addedItem = new ListItem<>(value);
        item.setNext(addedItem);

        ++count;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("[");
        ListItem<T> item = head;
        int i = 0;

        while (i < count - 1) {
            stringBuilder.append(item.getData());

            if (item.hasNext()) {
                stringBuilder.append(", ");
            }

            item = item.getNext();
            ++i;
        }

        stringBuilder.append(item.getData());

        stringBuilder.append("]");

        return stringBuilder.toString();
    }

    private boolean verifyArrayIndex(int index) {
        return (0 <= index) & (index < count);
    }
}
