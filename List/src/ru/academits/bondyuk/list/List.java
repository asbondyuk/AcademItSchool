package ru.academits.bondyuk.list;

public class List<T> {
    private T[] array;
    private int size;

    public int getSize() {
        return size;
    }

    public T getFirstElement() {
        if (array.length == 0) {
            return null;
        }

        return array[0];
    }

    public T getElement(int index) {
        if (index < 0) {
            throw new ArrayIndexOutOfBoundsException("Индекс массива должен быть больше нуля, получен: " + index);
        }

        if (index > array.length - 1) {
            throw new ArrayIndexOutOfBoundsException("Индекс запрашиваемого элемента больше длины массива. " +
                    "Максимальное значение:" + (array.length - 1) + "Получен: " + index);
        }

        return array[index];
    }

    public T setElement(int index, T value) {
        if (index < 0) {
            throw new ArrayIndexOutOfBoundsException("Индекс массива должен быть больше нуля, получен: " + index);
        }

        if (index > array.length - 1) {
            throw new ArrayIndexOutOfBoundsException("Индекс запрашиваемого элемента больше длины массива. " +
                    "Максимальное значение:" + (array.length - 1) + "Получен: " + index);
        }

        T oldValue = array[index];
        array[index] = value;

        return oldValue;
    }

    public T remove(int index) {
        if (index < 0) {
            throw new ArrayIndexOutOfBoundsException("Индекс массива должен быть больше нуля, получен: " + index);
        }

        if (index > array.length) {
            throw new ArrayIndexOutOfBoundsException("Индекс запрашиваемого элемента больше длины массива. " +
                    "Максимальное значение:" + (array.length - 1) + "Получен: " + index);
        }

        T oldValue = array[index];
        System.arraycopy(array, index + 1, array, index, array.length - index - 1);
        --size;

        return oldValue;
    }

    public void add(int index, T value){

    }

    public void addToStart(T value){
        add(0, value);
    }

    public boolean remove(T value){
        return false;
    }

    public T removeFromStart(){
        return null;
    }

    public void reverse(){

    }

    public List<T> copy() {

        return null;
    }
}
