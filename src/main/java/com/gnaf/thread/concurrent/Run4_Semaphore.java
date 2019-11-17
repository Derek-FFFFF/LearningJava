package com.gnaf.thread.concurrent;



import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

class Fat {

    private volatile double d;
    private static int counter = 0;
    private final int id = counter++;

    public Fat() {
        for (int i = 0; i < 10000; i++) {
            d += (Math.PI + Math.E) / (double) i;
        }
    }

    public void operation() {
        System.out.println(this);
    }

    @Override
    public String toString() {
        return "Fat id: " + id;
    }
}

class Pool<T> {

    private int size;
    private List<T> items = new ArrayList<>();
    private volatile boolean[] checkedOut;
    private Semaphore available;

    public Pool(Class<T> clazz, int size) {
        this.size = size;
        this.checkedOut = new boolean[size];
        this.available = new Semaphore(size, true);
        for (int i = 0; i < size; i++) {
            try {
                items.add(clazz.newInstance());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    public T checkOut() throws InterruptedException {
        available.acquire();
        return getItem();
    }

    public void checkIn(T x) {
        if (releaseItem(x)) {
            available.release();
        }
    }

    private synchronized T getItem() {
        for (int i = 0; i < size; i++) {
            if (!checkedOut[i]) {
                checkedOut[i] = true;
                return items.get(i);
            }
        }
        return null;
    }

    private synchronized boolean releaseItem(T item) {
        int index = items.indexOf(item);
        if (index == -1) {
            return false;
        }
        if (checkedOut[index]) {
            checkedOut[index] = false;
            return true;
        }
        return false;
    }

}

class CheckoutTask<T> implements Runnable {

    private static int counter = 0;
    private final int id = counter++;
    private Pool<T> pool;

    public CheckoutTask(Pool<T> pool) {
        this.pool = pool;
    }

    @Override
    public void run() {
        try {
            T item = pool.checkOut();
            System.out.println(this + " checked out " + item);
            TimeUnit.SECONDS.sleep(1);
            System.out.println(this + " checking in " + item);
            pool.checkIn(item);
        } catch (InterruptedException e) {

        }
    }

    @Override
    public String toString() {
        return "CheckoutTask " + id + " ";
    }
}

public class Run4_Semaphore {

    final static int SIZE = 25;

    public static void main(String[] args) throws Exception {
        final Pool<Fat> pool = new Pool<>(Fat.class, SIZE);
        ExecutorService exec = Executors.newCachedThreadPool();
        for (int i = 0; i < SIZE; i++) {
            exec.execute(new CheckoutTask<>(pool));
        }
        System.out.println("All CheckoutTasks created");
        List<Fat> list = new ArrayList<>();
        for (int i = 0; i < SIZE; i++) {
            Fat f = pool.checkOut();
            System.out.println(i + ": main() thread checked out");
            f.operation();
            list.add(f);
        }
        Future<?> blocked = exec.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    pool.checkOut();
                } catch (InterruptedException e) {
                    System.out.println("checkOut() Interrupted");
                }
            }
        });
        TimeUnit.SECONDS.sleep(2);
        blocked.cancel(true);
        System.out.println("Checking in objects in " + list);
        for (Fat f : list) {
            pool.checkIn(f);
        }
        for (Fat f : list) {
            pool.checkIn(f);
        }
        exec.shutdown();
    }

}
