package com.gnaf.basic.innerclass.demo02;

/**
 * @author Derek
 * @date 2019/9/20 22:00
 */
public class Run {

    public static void main(String[] args) {
        // default the RealName is static inner class
        ClassInInterface.RealName realName = new ClassInInterface.RealName();
        realName.f();

    }

}
