package com.gnaf.basic.innerclass.demo02;

/**
 * @author Derek
 * @date 2019/9/20 22:00
 */
public interface ClassInInterface {

    void f();

    class RealName implements ClassInInterface {

        @Override
        public void f() {
            System.out.println("RealName");
        }
    }

}
