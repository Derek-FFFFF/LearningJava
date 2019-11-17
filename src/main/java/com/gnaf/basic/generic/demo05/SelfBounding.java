package com.gnaf.basic.generic.demo05;

/**
 * @author Derek
 * @date 2019/10/22 22:15
 */
class SelfBounded<T extends SelfBounded<T>> {

    T element;

    SelfBounded<T> set(T arg) {
        this.element = arg;
        return this;
    }

    T get() {
        return this.element;
    }
}

class A extends SelfBounded<A> {}
class B extends SelfBounded<B> {}
class C extends SelfBounded<C> {
    C setAndGet(C arg) {
        set(arg);
        return get();
    }
}

class D {}
// case D didn't extends from SelfBounded
//class E extends SelfBounded<D> {}

class F extends SelfBounded{}

public class SelfBounding<T extends SelfBounding<T>> {

    public static void main(String[] args) {

        A a = new A();
        a.set(new A());
        a = a.set(new A()).get();
        C c = new C();
        c = c.setAndGet(new C());

    }

}
