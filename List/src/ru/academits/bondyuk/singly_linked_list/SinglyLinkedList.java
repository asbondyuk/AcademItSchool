package ru.academits.bondyuk.singly_linked_list;

public class SinglyLinkedList<T> {
    private ListItem<T> head;
    private int count;

    public SinglyLinkedList(T data) {
        head = new ListItem<>(data);
        ++count;
    }

    public SinglyLinkedList() {
        count = 0;
    }

    private void isIndexCorrect(int index) {
        if ((0 > index) && (index >= count)) {
            throw new ArrayIndexOutOfBoundsException("Некорректный индекс списка, должно быть значение от 0 до " + (count - 1)
                    + ". Получен: " + index);
        }
    }

    public int getCount() {
        return count;
    }

    public T getFirst() {
        if (count == 0) {
            throw new ArrayIndexOutOfBoundsException("Список пустой");
        }

        return head.getData();
    }

    public void addElementToStart(T data) {
        head = new ListItem<>(data, head);
        ++count;
    }

    public T removeFirst() {
        if (count == 0) {
            throw new ArrayIndexOutOfBoundsException("Список пустой");
        }

        T deletedItemData = head.getData();

        head = head.getNext();
        --count;

        return deletedItemData;
    }

    public T getElement(int index) {
        isIndexCorrect(index);

        ListItem<T> item = head;

        for (int i = 0; i < index; ++i) {
            item = item.getNext();
        }

        return item.getData();
    }

    public T setElement(int index, T data) {
        isIndexCorrect(index);

        ListItem<T> element = getListItem(index);
        T oldData = getElement(index);
        element.setData(data);

        return oldData;
    }

    public T remove(int index) {
        isIndexCorrect(index);

        if (index == 0) {
            removeFirst();
        }

        ListItem<T> previousItem = getListItem(index - 1);
        ListItem<T> deletedItem = previousItem.getNext();

        previousItem.setNext(deletedItem.getNext());

        --count;

        return deletedItem.getData();
    }

    public void add(int index, T data) {
        isIndexCorrect(index);

        ListItem<T> item = getListItem(index);

        if (item.hasNext()) {
            ListItem<T> nextItem = item.getNext();
            ListItem<T> newItem = new ListItem<>(data, nextItem);
            item.setNext(newItem);
        } else {
            ListItem<T> newItem = new ListItem<>(data);
            item.setNext(newItem);
        }

        ++count;
    }

    public boolean remove(T data) {
        int index = 0;

        for (ListItem<T> item = head; item.hasNext(); item = item.getNext()) {
            if (item.getData().equals(data)) {
                remove(index);
                return true;
            } else {
                ++index;
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

    public SinglyLinkedList<T> copy() {
        if (count == 0) {
            return new SinglyLinkedList<>();
        }

        SinglyLinkedList<T> newList = new SinglyLinkedList<>(head.getData());

        ListItem<T> copiedItem = head;
        ListItem<T> previousItem = newList.head;
        ListItem<T> item;

        do {
            copiedItem = copiedItem.getNext();
            item = new ListItem<>(copiedItem.getData());
            previousItem.setNext(item);
            previousItem = item;
            ++newList.count;
        } while (copiedItem.hasNext());

        return newList;
    }

    public void add(T data) {
        ListItem<T> item = getListItem(count - 1);

        ListItem<T> addedItem = new ListItem<>(data);
        item.setNext(addedItem);

        ++count;
    }

    @Override
    public String toString() {
        if (count == 0) {
            return "[]";
        }

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

    private ListItem<T> getListItem(int index) {
        ListItem<T> item = head;

        for (int i = 0; i < index; ++i) {
            item = item.getNext();
        }

        return item;
    }
}
