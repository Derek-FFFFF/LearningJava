package com.gnaf.basic.generic.demo06;

import com.gnaf.basic.constructor.demo01.B;
import com.gnaf.basic.generic.demo02.GenericArray;

/**
 * @author Derek
 * @date 2019/10/23 21:40
 */
class GenericSetter<T> {
    void set(T arg) {
        System.out.println("gGenericSetter.set(Base)");
    }
}

class DerivedGS extends GenericSetter<Base> {
    void set(Derived derived) {
        System.out.println("DerivedGS.set(Derived)");
    }
}

public class PlaninGenericInheritance {

    public static void main(String[] args) {
        Base base = new Base();
        Derived derived = new Derived();
        DerivedGS dgs = new DerivedGS();
        dgs.set(derived);
        dgs.set(base);
    }

}
