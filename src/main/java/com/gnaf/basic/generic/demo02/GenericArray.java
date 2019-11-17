package com.gnaf.basic.generic.demo02;

/**
 * @author Derek
 * @date 2019/10/20 22:39
 */
public class GenericArray<T> {

    private T[] array;

    public GenericArray() {
        array = (T[]) new Object[10];
    }

    public void put(int index, T item) {
        array[index] = item;
    }

    public T get(int index) {
        return array[index];
    }

    public T[] rep() {
        return array;
    }

}
