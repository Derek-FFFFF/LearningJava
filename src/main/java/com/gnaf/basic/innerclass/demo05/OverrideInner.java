package com.gnaf.basic.innerclass.demo05;

/**
 * @author Derek
 * @date 2019/9/21 17:39
 */
public class OverrideInner {

    protected class OverInner {
        public OverInner() {
            System.out.println("OverrideInner OverInner");
        }
    }

    public OverrideInner() {
        System.out.println("OverrideInner");
        new OverInner();
    }
}
