package com.gnaf.thread.shutdown;

import com.gnaf.basic.constructor.demo01.B;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class BlcokedMutex {
    private Lock lock = new ReentrantLock();

    public BlcokedMutex() {
        lock.lock();
    }

    public void f() {
        try {
            lock.lockInterruptibly();
            System.out.println("lock acquired in f()");
        } catch (InterruptedException e) {
            System.out.println("Interrupted from lock acquisition in f()");
        }
    }
}

class Blcoked2 implements Runnable {
    BlcokedMutex blcoked = new BlcokedMutex();

    @Override
    public void run() {
        System.out.println("Waiting for f() in BlockedMutex");
        blcoked.f();
        System.out.println("Broken out of blocked call");
    }
}

public class Run6_Interrupting2 {

    public static void main(String[] args) throws Exception {
        Thread t = new Thread(new Blcoked2());
        t.start();
        TimeUnit.SECONDS.sleep(1);
        System.out.println("Issuing t.interrupt()");
        t.interrupt();
    }

}
