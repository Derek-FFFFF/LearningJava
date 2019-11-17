package com.gnaf.basic.initialized.demo02;

/**
 * @author Derek
 * @date 2019/10/8 21:10
 */
public class A2 {

    static final int j = A3.random.nextInt();

    static {
        System.out.println("Load A2");
    }

}
