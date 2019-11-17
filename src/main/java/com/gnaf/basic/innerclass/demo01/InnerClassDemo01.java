package com.gnaf.basic.innerclass.demo01;

/**
 * @author Derek
 * @date 2019/9/17 20:31
 */
public class InnerClassDemo01 {

    public class Inner {
        // error no static field and method
//        public static int i = 1;
//        public static void f() {
//
//        }

        private class InnerInner {
        }

        // error no fucking static thing...
//        private static class InnerStatic {
//
//        }
    }


    public static class InnerStatic {
        private int i = 1;
        private static  int j = 2;
    }

}
