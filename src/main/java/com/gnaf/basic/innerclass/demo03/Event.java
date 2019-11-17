package com.gnaf.basic.innerclass.demo03;

/**
 * @author Derek
 * @date 2019/9/21 16:17
 */
public abstract class Event {

    private long eventTime;
    protected final long delayTime;

    public Event(long delayTime) {
        this.delayTime = delayTime;
        start();
    }

    public void start() {
        eventTime = System.nanoTime() + delayTime;
    }

    public boolean ready() {
        return System.nanoTime() >= eventTime;
    }

    public abstract void action();
}
