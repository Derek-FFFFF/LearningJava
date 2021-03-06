package com.gnaf.thread.problem.share.fix1;

import com.gnaf.thread.problem.share.EvenChecker;
import com.gnaf.thread.problem.share.IntGenerator;

public class SynchronizedEvenGenerator extends IntGenerator {
    private int currentEvenValue = 0;
    @Override
    public synchronized int next() {
        ++currentEvenValue;
        Thread.yield();
        ++currentEvenValue;
        return currentEvenValue;
    }

    public static void main(String[] args) {
        EvenChecker.test(new SynchronizedEvenGenerator(), 10);
    }
}
