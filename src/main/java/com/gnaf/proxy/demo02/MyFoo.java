package com.gnaf.proxy.demo02;

import com.gnaf.proxy.Person;

import java.lang.reflect.Method;

/**
 * @author Derek
 * @date 2019/10/6 11:00
 */
public class MyFoo implements MyInvocationHandler {

    private Person target;

    public Object getInstance(Person target) {
        this.target = target;
        Class clazz = target.getClass();
        return MyProxy.newProxyInstance(new MyClassLoader(), clazz.getInterfaces(), this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        method.invoke(target, args);
        System.out.println("invoke after");

        return null;
    }

}
