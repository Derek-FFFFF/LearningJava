package com.gnaf.basic.innerclass.demo01;

/**
 * @author Derek
 * @date 2019/9/17 20:32
 */
public class Run {

    public void test() {
        InnerClassDemo01 demo01 = new InnerClassDemo01();
        InnerClassDemo01.Inner inner = demo01.new Inner();
    }

    public static void main(String[] args) {



    }

}
