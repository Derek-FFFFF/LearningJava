package com.gnaf.basic.generic.demo03;

import com.gnaf.basic.constructor.demo01.A;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Derek
 * @date 2019/10/20 22:40
 */
public class Run {

    static <T> void writeExact(List<T> list, T item) {
        list.add(item);
    }

    static List<Apple> apples = new ArrayList<Apple>();
    static List<Fruit> fruit = new ArrayList<Fruit>();

    static void f1() {
        writeExact(apples, new Apple());
        writeExact(fruit, new Apple());
    }

    static <T> void writeWithWildcard(List<? super T> list, T item) {
        list.add(item);
    }

    static void f2() {
        writeWithWildcard(apples, new Apple());
        writeWithWildcard(fruit, new Apple());
    }

    public static void main(String[] args) {

        f1(); f2();

    }

}
