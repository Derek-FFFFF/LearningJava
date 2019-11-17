package com.gnaf.basic.generic.demo01;

import java.util.List;
import java.util.Map;

/**
 * @author Derek
 * @date 2019/10/20 9:33
 */
public class Run {

    static void f(Map<String, List<? extends Number>> param) {

    }

    public static void main(String[] args) {
//        f(New.map());
        f(New.<String, List<? extends Number>>map());
    }

}
