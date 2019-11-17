package com.gnaf.thread.problem.atomic.fix1;

import com.gnaf.thread.problem.share.IntGenerator;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicEvenGenerator extends IntGenerator {

    private AtomicInteger ai = new AtomicInteger(0);

    @Override
    public int next() {
        return ai.addAndGet(2);
    }
}
