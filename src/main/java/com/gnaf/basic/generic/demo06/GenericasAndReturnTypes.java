package com.gnaf.basic.generic.demo06;

/**
 * @author Derek
 * @date 2019/10/22 22:24
 */
interface GenericGetter<T extends GenericGetter<T>> {
    T get();
}

interface Getter extends GenericGetter<Getter> {

}

public class GenericasAndReturnTypes {

    void test(Getter g) {
        Getter g1 = g.get();
        GenericGetter g2 = g.get();
    }

}
