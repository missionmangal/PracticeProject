package com.myapplication.threads;

/*Suppose you have 2 threads (Thread-1 and Thread-2) on same object.
Thread-1 is in static synchronized method1(), can Thread-2 enter
static synchronized method2() at same time?*/

public class SynchronizationPractice2 {

    public static void main(String[] args) {
        Runnable1 runnable1 = new Runnable1();
        Thread thread1 = new Thread(runnable1, "Thread 1");
        Thread thread2 = new Thread(runnable1, "Thread 2");
        thread1.start();
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread2.start();
    }
}

class Runnable1 implements Runnable {

    @Override
    public void run() {
        if (Thread.currentThread().getName().equals("Thread 1")) {
            method1();
        } else {
            method2();
        }
    }

    private static synchronized void method1() {
        System.out.println(System.currentTimeMillis());
        System.out.println("In method 1 ");
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static synchronized void method2() {
        System.out.println(System.currentTimeMillis());
        System.out.println("In method 2 ");
    }
}

