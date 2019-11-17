package com.gnaf.basic.innerclass.demo04;

/**
 * @author Derek
 * @date 2019/9/21 17:18
 */
public class InnerClassExtend extends Outer.OuterInner {

    // cause inner class need a object of Outer class.
    public InnerClassExtend(Outer outter) {
        outter.super();
    }

    class Inner {

    }

    class InnerSon extends Inner {

    }

    public static void main(String[] args) {
        // here we got a instance of Outer class.
        InnerClassExtend ice = new InnerClassExtend(new Outer());
        InnerClassExtend.InnerSon son = ice.new InnerSon();
    }

}
