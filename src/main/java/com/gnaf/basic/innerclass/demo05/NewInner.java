package com.gnaf.basic.innerclass.demo05;

/**
 * @author Derek
 * @date 2019/9/21 17:40
 */
public class NewInner extends OverrideInner {

    protected class OverInner {
        public OverInner() {
            System.out.println("NewInner OverInner");
        }
    }

    public static void main(String[] args) {
        // even if we extend OverrideInner and OverInner
        // we still got the instance of OverrideInner.OverInner
        // not NewInner.OverInner
        // unless u extend the OverInner from OverrideInner specific
        new NewInner();
    }

}
