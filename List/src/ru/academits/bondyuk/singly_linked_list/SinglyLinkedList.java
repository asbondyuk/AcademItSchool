package ru.academits.bondyuk.singly_linked_list;

import java.util.NoSuchElementException;
import java.util.Objects;

public class SinglyLinkedList<T> {
    private ListItem<T> head;
    private int count;

    public SinglyLinkedList(T data) {
        head = new ListItem<>(data);
        ++count;
    }

    public SinglyLinkedList() {
    }

    private void checkIndex(int index) {
        if ((index < 0) && (index >= count)) {
            throw new ArrayIndexOutOfBoundsException("Некорректный индекс списка, должно быть значение от 0 до " + (count - 1)
                    + ". Получен: " + index);
        }
    }

    public int getCount() {
        return count;
    }

    public T getFirst() {
        if (count == 0) {
            throw new NoSuchElementException("Список пустой");
        }

        return head.getData();
    }

    public void addFirst(T data) {
        head = new ListItem<>(data, head);
        ++count;
    }

    public T removeFirst() {
        if (count == 0) {
            throw new NoSuchElementException("Список пустой");
        }

        T deletedItemData = head.getData();

        head = head.getNext();
        --count;

        return deletedItemData;
    }

    public T getElement(int index) {
        ListItem<T> item = getListItem(index);

        return item.getData();
    }

    private ListItem<T> getListItem(int index) {
        checkIndex(index);

        ListItem<T> item = head;

        for (int i = 0; i < index; ++i) {
            item = item.getNext();
        }

        return item;
    }

    public T setElement(int index, T data) {
        checkIndex(index);

        ListItem<T> element = getListItem(index);
        T oldData = element.getData();
        element.setData(data);

        return oldData;
    }

    public T removeByIndex(int index) {
        checkIndex(index);

        if (index == 0) {
            return removeFirst();
        }

        ListItem<T> previousItem = getListItem(index - 1);
        ListItem<T> deletedItem = previousItem.getNext();

        previousItem.setNext(deletedItem.getNext());

        --count;

        return deletedItem.getData();
    }

    public void add(int index, T data) {
        checkIndex(index - 1);

        ListItem<T> item = getListItem(index - 1);
        item.setNext(new ListItem<>(data, item.getNext()));

        ++count;
    }

    public int getIndex(T data) {
        if (count == 0) {
            return -1;
        }

        ListItem<T> item = head;

        for (int i = 0; i < count; ++i) {
            if (Objects.equals(item.getData(), data)) {
                return i;
            } else {
                item = item.getNext();
            }
        }

        return -1;
    }

    public boolean remove(T data) {
        ListItem<T> currentItem = head;
        if (Objects.equals(currentItem.getData(), data)) {
            removeFirst();
            --count;
            return true;
        }

        while (currentItem.hasNext()) {
            ListItem<T> previousItem = currentItem;
            currentItem = currentItem.getNext();

            if (Objects.equals(currentItem.getData(), data)) {
                previousItem.setNext(currentItem.getNext());
                --count;
                return true;
            }
        }

        return false;
    }

    public void reverse() {
        if (count < 1) {
            return;
        }

        ListItem<T> previousItem = head;
        ListItem<T> currentItem = head.getNext();

        previousItem.setNext(null); // разворачиваем next

        while (currentItem.hasNext()) {
            ListItem<T> tmp = previousItem;
            previousItem = currentItem;
            currentItem = currentItem.getNext();
            previousItem.setNext(tmp);
        }

        currentItem.setNext(previousItem);
        head = currentItem;
    }

    public SinglyLinkedList<T> copy() {
        if (count == 0) {
            return new SinglyLinkedList<>();
        }

        SinglyLinkedList<T> newList = new SinglyLinkedList<>(head.getData());
        newList.count = this.count;

        ListItem<T> copiedItem = head;
        ListItem<T> previousItem = newList.head;

        while (copiedItem.hasNext()) {
            copiedItem = copiedItem.getNext();
            ListItem<T> item = new ListItem<>(copiedItem.getData());
            previousItem.setNext(item);
            previousItem = item;
        }

        return newList;
    }

    public void add(T data) {
        if (count == 0) {
            head = new ListItem<>(data);
            return;
        }

        ListItem<T> item = getListItem(count - 1);
        item.setNext(new ListItem<>(data));
        ++count;
    }

    @Override
    public String toString() {
        if (count == 0) {
            return "[]";
        }

        StringBuilder stringBuilder = new StringBuilder("[");
        ListItem<T> item = head;

        while (item.hasNext()) {
            stringBuilder.append(item.getData());

            if (item.hasNext()) {
                stringBuilder.append(", ");
            }

            item = item.getNext();
        }

        stringBuilder.append(item.getData());
        stringBuilder.append("]");

        return stringBuilder.toString();
    }
}
