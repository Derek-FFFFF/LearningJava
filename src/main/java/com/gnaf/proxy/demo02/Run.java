package com.gnaf.proxy.demo02;

import com.gnaf.proxy.Man;
import com.gnaf.proxy.Person;

/**
 * @author Derek
 * @date 2019/10/6 12:33
 */
public class Run {


    public static void main(String[] args) {

        Person person = (Person) new MyFoo().getInstance(new Man());
        person.speak();

    }

}
