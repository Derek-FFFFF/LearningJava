package com.gnaf.basic.initialized.demo03;

/**
 * @author Derek
 * @date 2019/10/9 19:12
 */
public class StaticFinal {

    static int i = 1;
    static final int j = 2;

    static {
        System.out.println("Load StaticFinal");
    }

}
