package com.gnaf.thread.concurrent;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

class DelayedTask implements Runnable, Delayed {
    private static int counter = 0;
    private final int id = counter++;
    private final int delta;
    private final long trigger;
    protected static List<DelayedTask> sequence = new ArrayList<>();

    public DelayedTask(int delta) {
        this.delta = delta;
        trigger = System.nanoTime() + TimeUnit.NANOSECONDS.convert(delta, TimeUnit.MILLISECONDS);
        sequence.add(this);
    }

    @Override
    public long getDelay(TimeUnit unit) {
        return unit.convert(trigger - System.nanoTime(), TimeUnit.NANOSECONDS);
    }

    @Override
    public int compareTo(Delayed o) {
        DelayedTask task = (DelayedTask) o;
        if (trigger < task.trigger) return -1;
        if (trigger > task.trigger) return 1;
        return 0;
    }

    @Override
    public void run() {
        System.out.println(this + " ");
    }

    @Override
    public String toString() {
        return String.format("[%1$-4d]", delta) + " Task " + id;
    }

    public String summary() {
        return "(" + id + ":" + delta + ")";
    }

    public static class EndSentinel extends DelayedTask {
        private ExecutorService exec;
        public EndSentinel(int delay, ExecutorService exec) {
            super(delay);
            this.exec = exec;
        }

        @Override
        public void run() {
            for (DelayedTask dt : sequence) {
                System.out.println(dt.summary() + " ");
            }
            System.out.println(this + " Calling ShutdownNow()");
            exec.shutdownNow();
        }
    }
}

class DelayedTaskConsumer implements Runnable {
    private DelayQueue<DelayedTask> q;

    public DelayedTaskConsumer(DelayQueue<DelayedTask> q) {
        this.q = q;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                q.take().run();
            }
        } catch (InterruptedException e) {

        }
        System.out.println("Finished DelayedTaskConsumer");
    }
}

public class Run3_DelayQueue {

    public static void main(String[] args) {
        Random rand = new Random(47);
        ExecutorService exec = Executors.newCachedThreadPool();
        DelayQueue<DelayedTask> queue = new DelayQueue<>();
        for (int i = 0; i < 20; i++) {
            queue.put(new DelayedTask(rand.nextInt(5000)));
        }
        queue.add(new DelayedTask.EndSentinel(5000, exec));
        exec.execute(new DelayedTaskConsumer(queue));
    }

}
