package com.gnaf.basic;

/**
 * @author Derek
 * @date 2019/7/1 22:48
 */
public class TestAutoBoxing {

    public static void main(String[] args) {
        // to improve auto boxing. Integer will call a.intValue
        // when its null. it will crash u meet NullPointerException
        Integer a = null;
        int b = 1;
        System.out.println(a == b);

    }

}
