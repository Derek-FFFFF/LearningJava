package com.gnaf.thread.basic;

import java.util.concurrent.TimeUnit;

public class Run2_Sleep {

    public static synchronized void f() {
        System.out.println(Thread.currentThread().getName() + " Get lock");
        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + " Release lock");
    }

    // Old Style: Thread.sleep
    // Now TimeUnit.SECONDS.sleep

    public static void main(String[] args) {
        new Thread() {
            @Override
            public void run() {
                Run2_Sleep.f();
            }
        }.start();

        new Thread() {
            @Override
            public void run() {
                Run2_Sleep.f();
            }
        }.start();
    }

}
