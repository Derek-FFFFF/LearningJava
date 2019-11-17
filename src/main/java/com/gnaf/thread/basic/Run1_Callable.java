package com.gnaf.thread.basic;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

public class Run1_Callable {

    public static void main(String[] args) throws Exception {
        Callable<Integer> ci = new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                TimeUnit.SECONDS.sleep(3);
                return 1;
            }
        };

        FutureTask<Integer> ft = new FutureTask<>(ci);
        Thread t1 = new Thread(ft);
        t1.start();

        System.out.println(ft.get());
    }

}
