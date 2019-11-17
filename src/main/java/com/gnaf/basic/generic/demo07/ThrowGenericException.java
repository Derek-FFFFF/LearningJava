package com.gnaf.basic.generic.demo07;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Derek
 * @date 2019/10/23 21:46
 */
interface Processor<T, E extends Exception> {
    void process(List<T> result) throws E;
}

class ProcessRunner<T, E extends Exception> extends ArrayList<Processor<T, E>> {
    List<T> processAll() throws E {
        List<T> result = new ArrayList<T>();
        for (Processor<T, E> processor : this) {
            processor.process(result);
        }
        return result;
    }
}

class Failure1 extends Exception {}
class Processor1 implements Processor<String, Failure1> {

    static int count = 2;

    @Override
    public void process(List<String> result) throws Failure1 {
        if (count-- > 0) {
            result.add("Hep!");
        } else {
            result.add("Ho!");
        }
        if (count < 0) {
            throw new Failure1();
        }
    }
}

class Failure2 extends Exception {}
class Processor2 implements Processor<Integer, Failure2> {

    static int count = 2;

    @Override
    public void process(List<Integer> result) throws Failure2 {
        if (count-- == 0) {
            result.add(47);
        } else {
            result.add(11);
        }
        if (count < 0) {
            throw new Failure2();
        }
    }
}

public class ThrowGenericException {

    public static void main(String[] args) {
        ProcessRunner<String, Failure1> runner = new ProcessRunner<String, Failure1>();
        for (int i = 0; i < 3; i++) {
            runner.add(new Processor1());
        }

        try {
            System.out.println(runner.processAll());
        } catch (Failure1 e) {
            System.out.println(e);
        }

        ProcessRunner<Integer, Failure2> runner2 = new ProcessRunner<Integer, Failure2>();
        for (int i = 0; i < 3; i++) {
            runner2.add(new Processor2());
        }

        try {
            System.out.println(runner2.processAll());
        } catch (Failure2 e) {
            System.out.println(e);
        }
    }

}
