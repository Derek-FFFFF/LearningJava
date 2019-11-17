package com.gnaf.basic.initialized.demo01;

/**
 * @author Derek
 * @date 2019/8/26 22:30
 */
public class Run {

    public static void main(String[] args) {
        test3();
    }

    /**
     * Class.forName will trigger constructor
     */
    public static void test1() {
        try {
            Class.forName("com.gnaf.basic.initialized.demo01.B");
        } catch (ClassNotFoundException e) {

        }
    }

    /**
     * static final field won't trigger class load
     */
    public static void test2() {
        System.out.println(B.q);
        System.out.println("---------------------");
        System.out.println(B.w);
    }

    /**
     * parent static filed won't trigger son class load
     */
    public static void test3() {
        System.out.println(A.i);
        System.out.println("---------------------");
        System.out.println(A.j);
    }

}
