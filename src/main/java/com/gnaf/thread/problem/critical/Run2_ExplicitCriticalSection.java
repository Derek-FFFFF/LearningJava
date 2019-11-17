package com.gnaf.thread.problem.critical;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class ExplicitPairManager1 extends PairManager {
    private Lock lock = new ReentrantLock();

    @Override
    public synchronized void increment() {
        lock.lock();
        try {
            p.incrementX();
            p.incrementY();
            store(getPair());
        } finally {
            lock.unlock();
        }
    }
}

class ExplicitPairManager2 extends PairManager {

    private Lock lock = new ReentrantLock();

    @Override
    public void increment() {
        Pair temp;
        lock.lock();
        try {
            p.incrementX();
            p.incrementY();
            temp = getPair();
        } finally {
            lock.unlock();
        }
        store(temp);
    }
}

public class Run2_ExplicitCriticalSection {

    public static void main(String[] args) {
        PairManager pm1 = new ExplicitPairManager1();
        PairManager pm2 = new ExplicitPairManager2();
        Run1_CriticalSection.testApproaches(pm1, pm2);
    }

}
