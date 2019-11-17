package com.gnaf.basic.initialized.demo02;

import java.util.Random;

/**
 * @author Derek
 * @date 2019/10/8 21:12
 */
public class A3 {

    static final Random random = new Random();

    static {
        System.out.println("Load A3");
    }

}
