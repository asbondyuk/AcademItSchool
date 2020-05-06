package ru.academits.bondyuk.hash_table;

import java.util.*;

public class HashTable<T> implements Collection<T> {
    private ArrayList<T>[] hashCodes;
    private int size;
    private int modCount;

    private class HashTableIterator implements Iterator<T> {
        private int currentIndex;
        private int modCount;

        public HashTableIterator() {
            currentIndex = -1;
            modCount = HashTable.this.modCount;
        }

        @Override
        public boolean hasNext() {
            return currentIndex + 1 < size;
        }

        @Override
        public T next() {
            if (modCount != HashTable.this.modCount) {
                throw new ConcurrentModificationException("Коллекция была изменена до завершения работы итератора");
            }

            ++currentIndex;

            if (currentIndex == size) {
                throw new NoSuchElementException("Элементы в списке закончились");
            }

            return null;
        }

//        @Override
//        public void remove() {
//            items[currentIndex] = null;
//        }
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
        return false;
    }

    @Override
    public Iterator<T> iterator() {
        return null;
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        return null;
    }

    @Override
    public boolean add(T t) {
        // Todo ?Проверка наличия объекта в списке
        int hashCode = t.hashCode();

        if (hashCodes[hashCode] == null) {
            hashCodes[hashCode] = new ArrayList<>(1);
            hashCodes[hashCode].add(t);
            return true;
        }

        hashCodes[hashCode].add(t);

        return true;
    }

    @Override
    public boolean remove(Object o) {
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public void clear() {

    }
}
