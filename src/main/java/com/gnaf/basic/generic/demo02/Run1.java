package com.gnaf.basic.generic.demo02;

/**
 * @author Derek
 * @date 2019/10/20 21:45
 */
public class Run1 {

    static Generic<Integer>[] ints1 = new Generic[10];
    // compile error
//    static Generic<Integer>[] ints2 = new Generic<Integer>[10];
    // runtime error ClassCastException
//    static Generic<Integer>[] ints3 = (Generic<Integer>[]) new Object[10];
    static Generic<Integer>[] ints4 = (Generic<Integer>[]) new Generic[10];

    public static void main(String[] args) {

        ints1[0] = new Generic<Integer>();
        ints1[1] = new Generic();
        // compile error
//        ints1[2] = new Generic<Object>();
        // runtime error ClassCastException
//        ints1[3] = (Generic<Integer>) new Object();


    }

}
