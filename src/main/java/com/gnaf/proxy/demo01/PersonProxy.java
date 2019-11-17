package com.gnaf.proxy.demo01;

import com.gnaf.proxy.Person;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author Derek
 * @date 2019/10/4 21:10
 */
public class PersonProxy implements InvocationHandler {

    private Person target;

    public PersonProxy(Person target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (Object.class.equals(method.getDeclaringClass())) {
            return method.invoke(this, args);
        }
        method.invoke(target, args);
        return null;
    }
}
