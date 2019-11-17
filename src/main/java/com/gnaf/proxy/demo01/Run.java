package com.gnaf.proxy.demo01;

import com.gnaf.proxy.Man;
import com.gnaf.proxy.Person;
import sun.misc.ProxyGenerator;

import java.io.FileOutputStream;
import java.lang.reflect.Proxy;

/**
 * @author Derek
 * @date 2019/10/4 21:10
 */
public class Run {

    public static void main(String[] args) throws Exception {

        Person person = new Man();
        PersonProxy proxy = new PersonProxy(person);

        Person p1 = (Person) Proxy.newProxyInstance(Person.class.getClassLoader(), new Class[]{Person.class}, proxy);
        p1.speak();
        System.out.println(p1.hashCode());

        // 1. get the source obj of being proxied, and get the implements interfaces
        // 2. jdk will generate a new class which implements the interfaces we get
        // 3. hold the source obj of being proxied
        // 4. generate a class byte code dynamic
        // 5. compile
        byte[] data = ProxyGenerator.generateProxyClass("$Proxy0", new Class[]{Person.class});
        FileOutputStream os = new FileOutputStream("./$Proxy0.class");
        os.write(data);
        os.close();


    }
}
