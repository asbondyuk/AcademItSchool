package ru.academits.bondyuk.array_list;

import java.util.*;

public class ArrayList<E> implements List<E> {
    private static final int DEFAULT_CAPACITY = 10;

    private E[] items;
    private int size;
    private int modCount; // число изменений

    @SuppressWarnings("unchecked")
    public ArrayList() {
        items = (E[]) new Object[DEFAULT_CAPACITY];
        size = 0;
        modCount = 0;
    }

    public ArrayList(E[] array) {
        items = array;
        size = array.length;
        modCount = 0;
    }

    @SuppressWarnings("unchecked")
    public ArrayList(int capacity) {
        items = (E[]) new Object[capacity];
        size = 0;
        modCount = 0;
    }

    private class ArrayListIterator implements Iterator<E> {
        private int currentIndex;
        private int modCount;

        public ArrayListIterator() {
            currentIndex = -1;
            modCount = ArrayList.this.modCount;
        }

        @Override
        public boolean hasNext() {
            return currentIndex + 1 < size;
        }

        @Override
        public E next() {
            if (modCount != ArrayList.this.modCount) {
                throw new ConcurrentModificationException("Коллекция была изменена до завершения работы итератора");
            }

            ++currentIndex;

            if (currentIndex == size) {
                throw new NoSuchElementException("Элементы в списке закончились");
            }

            return items[currentIndex];
        }

        @Override
        public void remove() {
            items[currentIndex] = null;
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(Object o) {
        Iterator<E> iterator = new ArrayListIterator();

        while (iterator.hasNext()) {
            if (Objects.equals(iterator.next(), o)) {
                return true;
            }
        }

        return false;
    }

    @Override
    public Iterator<E> iterator() {
        return new ArrayListIterator();
    }

    @Override
    public Object[] toArray() {
        Object[] objects = new Object[size];
        System.arraycopy(items, 0, objects, 0, size);

        return objects;
    }

    @Override
    @SuppressWarnings("unchecked")
//    смысл данного метода не ясен
    public <T> T[] toArray(T[] array) {
        T[] intersectionArray = (T[]) new Object[array.length];
        int intersectionCount = 0;

        for (E item : items) {
            for (T t : array) {
                if (Objects.equals(item, t)) {
                    intersectionArray[intersectionCount++] = t;
                    break;
                }
            }
        }

        return intersectionArray;
    }

    public void ensureCapacity(int minCapacity) {
        if (items.length < minCapacity) {
            int increasingCoefficient = (int) Math.ceil((double) minCapacity / items.length);
            items = Arrays.copyOf(items, items.length * increasingCoefficient);
        }
    }

    public void increaseCapacity() {
        items = Arrays.copyOf(items, items.length * 2);
        ++modCount;
    }

    public void trimToSize() {
        if (size < items.length - 1) {
            items = Arrays.copyOf(items, size);
        }

        ++modCount;
    }

    @Override
    public boolean add(E e) {
        if (size >= items.length) {
            increaseCapacity();
        }

        items[size] = e;
        ++modCount;
        ++size;

        return true;
    }

    @Override
    public boolean remove(Object o) {
        Iterator<E> iterator = iterator();

        while (iterator.hasNext()) {
            if (Objects.equals(iterator.next(), o)) {
                iterator.remove();

                return true;
            }
        }

        ++modCount;
        --size;

        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        for (Object o : c) {
            if (!contains(o)) {
                return false;
            }
        }

        return true;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        int newListSize = size + c.size();
        if (newListSize >= items.length) {
            ensureCapacity(newListSize);
        }

        for (E e : c) {
            add(e);
        }

        ++modCount;
        return true;
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        int newListSize = size + c.size();
        if (newListSize >= items.length) {
            ensureCapacity(newListSize);
        }

        int addedPosition = index;
        for (E e : c) {
            add(addedPosition, e);
            ++addedPosition;
        }

        ++modCount;
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        for (Object o : c) {
            remove(o);
        }

        ++modCount;
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        removeIf(e -> !c.contains(e));
//        оптимизация idea периодически пугает. Первоначальный код:
//        while (iterator.hasNext()) {
//            if (!c.contains(iterator.next())) {
//                iterator.remove();
//            }
//        }
        ++modCount;
        return true;
    }

    @Override
    public void clear() {
        Arrays.fill(items, null);
        size = 0;
        ++modCount;
    }

    private void checkIndex(int index) {
        if ((index < 0) && (index >= size)) {
            throw new ArrayIndexOutOfBoundsException("Некорректный индекс списка, должно быть значение от 0 до " + (size - 1)
                    + ". Получен: " + index);
        }
    }

    @Override
    public E get(int index) {
        checkIndex(index);

        return items[index];
    }

    @Override
    public E set(int index, E element) {
        checkIndex(index);

        E oldValue = items[index];
        items[index] = element;

        return oldValue;
    }

    @Override
    public void add(int index, E element) {
        checkIndex(index);

        if (size >= items.length - 1) {
            increaseCapacity();
        }

        if (size - index >= 0) {
            System.arraycopy(items, index, items, index + 1, size - index);
        }

        items[index] = element;
        ++modCount;
        ++size;
    }

    @Override
    public E remove(int index) {
        checkIndex(index);

        E oldValue = items[index];
        items[index] = null;

        return oldValue;
    }

    @Override
    public int indexOf(Object o) {
        for (int i = 0; i < items.length; ++i) {
            if (Objects.equals(items[i], o)) {
                return i;
            }
        }

        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        for (int i = items.length - 1; i >= 0; --i) {
            if (Objects.equals(items[i], o)) {
                return i;
            }
        }

        return -1;
    }

    @Override
    public String toString() {
        return Arrays.toString(items);
    }

    // последующие методы не требуют реализации по условиям задачи
    @Override
    public ListIterator<E> listIterator() {
        return null;
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        return null;
    }

    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        return null;
    }
}