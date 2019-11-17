package com.gnaf.basic.constructor.demo03;

/**
 * @author Derek
 * @date 2019/9/2 22:25
 */
public class B extends A {

    private int i = 0;

    public B() {
        say();
        i = 1;
        say();
    }

    public void say() {
        System.out.println("B Say: " + i);
    }

}
