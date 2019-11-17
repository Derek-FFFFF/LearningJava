package com.gnaf.basic.exception.demo01;

/**
 * @author Derek
 * @date 2019/9/27 22:27
 */
public class Run {

    public static void main(String[] args) throws Exception {

        try {
            throw new NullPointerException();
        } catch (Exception e) {
            throw e;
        } finally {
            System.out.println("re throw will trigger finally");
        }

    }

}
