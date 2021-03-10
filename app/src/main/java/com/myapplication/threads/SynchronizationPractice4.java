package com.myapplication.threads;


/*
Suppose you have thread and it is in synchronized method and
now can thread enter other synchronized method from that method?
 * */
public class SynchronizationPractice4 {
    public static void main(String[] args) {
        Runnable4 runnable = new Runnable4();
        Thread thread1 = new Thread(runnable, "Thread 1");
        Thread thread2 = new Thread(runnable, "Thread 2");
        thread1.start();
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread2.start();
    }
}

class Runnable4 implements Runnable {

    @Override
    public void run() {
        if (Thread.currentThread().getName().equals("Thread 1")) {
            method1();
        } else {
            method2();
        }
    }

    private synchronized void method1() {
        System.out.println(System.currentTimeMillis());
        System.out.println(Thread.currentThread().getName()+ " In method 1 ");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        method2();
    }

    private synchronized void method2() {
        System.out.println(System.currentTimeMillis());
        System.out.println(Thread.currentThread().getName()+ " In method 2 ");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}