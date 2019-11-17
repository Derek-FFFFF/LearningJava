package com.gnaf.basic.generic.demo06;

/**
 * @author Derek
 * @date 2019/10/22 22:22
 */
class Base {
}

class Derived extends Base {
}

interface OrdinaryGetter {
    Base get();
}

interface DerivedGetter extends OrdinaryGetter {
    Derived get();
}

public class CovariantReturnTypes {

    void test(DerivedGetter getter) {
        Derived derived = getter.get();
    }

}
