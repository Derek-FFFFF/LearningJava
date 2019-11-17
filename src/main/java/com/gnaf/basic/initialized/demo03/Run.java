package com.gnaf.basic.initialized.demo03;

/**
 * @author Derek
 * @date 2019/10/9 19:13
 */
public class Run {

    public static void main(String[] args) {

//        System.out.println(StaticFinal.i);
        // final won't trigger class load
        // cause final will be init
        System.out.println(StaticFinal.j);

    }

}
