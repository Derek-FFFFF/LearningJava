package com.gnaf.basic.constructor.demo01;

/**
 * @author Derek
 * @date 2019/8/14 21:17
 */
public class B extends A {
    static {
        System.out.println("B static block1");
    }
    {
        System.out.println("B block1");
    }
    Foo f3 = new Foo(3);
    public B() {
        System.out.println("B");
    }
    static {
        System.out.println("B static block2");
    }
    {
        System.out.println("B block2");
    }
}
