package com.gnaf.basic.generic.demo06;

/**
 * @author Derek
 * @date 2019/10/23 21:38
 */
interface SelfBoundSetter<T extends SelfBoundSetter<T>> {
    void set(T arg);
}

interface Setter extends SelfBoundSetter<Setter> {}

public class SelfBoundingAndCovariantArguments {

    void testA(Setter s1, Setter s2, SelfBoundSetter sbs) {
        s1.set(s2);
//        s1.set(sbs);
    }

}
