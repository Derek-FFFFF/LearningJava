package com.gnaf.basic.constructor.demo01;

/**
 * @author Derek
 * @date 2019/8/14 21:16
 */
public class A {
    {
        System.out.println("A block1");
    }
    static {
        System.out.println("A static block1");
    }
    static Foo f4 = new Foo(4);
    Foo f1 = new Foo(1);
    public A() {
        System.out.println("A");
    }
    Foo f2 = new Foo(2);
    static Foo f5 = new Foo(5);
    static {
        System.out.println("A static block2");
    }
    {
        System.out.println("A block2");
    }
}
