package com.gnaf.proxy.demo02;

import java.lang.reflect.Method;

/**
 * @author Derek
 * @date 2019/10/6 10:57
 */
public interface MyInvocationHandler {

    Object invoke(Object proxy, Method method, Object[] args) throws Throwable;

}
