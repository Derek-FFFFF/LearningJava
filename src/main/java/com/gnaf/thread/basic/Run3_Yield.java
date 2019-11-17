package com.gnaf.thread.basic;

public class Run3_Yield {

    public static synchronized void f() {
        while (true) {
            System.out.println(Thread.currentThread().getName() + " Get Lock");
            Thread.yield();
            System.out.println(Thread.currentThread().getName() + " Yield");
        }
    }

    public static void main(String[] args) {

        new Thread() {
            @Override
            public void run() {
                Run3_Yield.f();
            }
        }.start();

        new Thread() {
            @Override
            public void run() {
                Run3_Yield.f();
            }
        }.start();

    }

}
