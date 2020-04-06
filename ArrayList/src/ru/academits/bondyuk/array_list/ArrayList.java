package ru.academits.bondyuk.array_list;

import java.util.*;

public class ArrayList<E> implements List<E> {
    private E[] items;
    private int size;
    private int modCount; // число изменений

    public ArrayList() {
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
            if (iterator.next().equals(o)) {
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

        for (int i = 0; i < size; ++i) {
            objects[i] = items[i];
        }

        return objects;
    }

    @Override
    public <T> T[] toArray(T[] a) {
//        E[] array = items;
//
//        for (int i = 0; i < size; ++i) {
//            objects[i] = items[i];
//        }

        return null;
    }

    @Override
    public boolean add(E e) {
        ++modCount;


        return false;
    }

    @Override
    public boolean remove(Object o) {
        ++modCount;
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        ++modCount;
        return false;
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        ++modCount;
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        ++modCount;
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        ++modCount;
        return false;
    }

    @Override
    public void clear() {
        ++modCount;
    }

    @Override
    public E get(int index) {
        return items[index];
    }

    @Override
    public E set(int index, E element) {
        return null;
    }

    @Override
    public void add(int index, E element) {
        ++modCount;
    }

    @Override
    public E remove(int index) {
        ++modCount;
        return null;
    }

    @Override
    public int indexOf(Object o) {
        return 0;
    }

    @Override
    public int lastIndexOf(Object o) {
        return 0;
    }

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