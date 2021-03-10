package com.myapplication.threads;


// Use of synchronized block on object
public class ThreadSynchronized {
    public static void main(String[] args) {
        String str = "Vishnu";
        Thread thread1 = new Thread(new ThreadSyn1(str), "ThreadSyn1");
        Thread thread2 = new Thread(new ThreadSyn2(str), "ThreadSyn2");
        thread1.start();
        try {
            Thread.sleep(200);
        } catch (InterruptedException ignored) {

        }
        thread2.start();

    }
}

class ThreadSyn1 implements Runnable {
    ThreadSyn1(String str) {
        this.str = str;
    }
    String str;

    public void setStr(String str) {
        this.str = str;
    }

    @Override
    public void run() {
        int n = 11;
        synchronized (str) {
            for (int i = 1; i < n; i++) {
                System.out.println(Thread.currentThread().getName() + " " + i);
                try {
                    str.notify();
                    if (i != n - 1)
                        str.wait();
                } catch (InterruptedException ignored) {

                }
            }
        }
    }
}

class ThreadSyn2 implements Runnable {

    ThreadSyn2(String str) {
        this.str = str;
    }

    String str;

    @Override
    public void run() {
        int n = 11;
        synchronized (str) {
            for (int i = 1; i < n; i++) {
                System.out.println(Thread.currentThread().getName() + " " + i);
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                try {
                    str.notify();
                    if (i != n - 1)
                        str.wait();
                } catch (InterruptedException ignored) {
                }
            }
        }
    }
}
