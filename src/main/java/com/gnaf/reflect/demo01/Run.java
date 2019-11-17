package com.gnaf.reflect.demo01;

/**
 * @author Derek
 * @date 2019/10/8 20:41
 */
public class Run {


    public static void main(String[] args) throws Exception {

        Class<?> clazz = Class.forName("com.gnaf.reflect.ReflectObject");
        clazz.newInstance();
        // it must have default constructor
    }

}
