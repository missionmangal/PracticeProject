package com.myapplication.threads;


/*
Suppose you have thread and it is in synchronized method and
now can thread enter other synchronized method from that method?
 * */
public class SynchronizationPractice5 {
    public static void main(String[] args) {
        Runnable5 runnable = new Runnable5();
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

class Runnable5 implements Runnable {

    Runnable5 runnable5 ;
    Runnable5(){
        runnable5 = this;
    }
    @Override
    public void run() {
        if (Thread.currentThread().getName().equals("Thread 1")) {
            method1(this);
        } else {
            method2();
        }
    }

    private static synchronized void method1(Runnable5 runnable5) {
        System.out.println(System.currentTimeMillis());
        System.out.println(Thread.currentThread().getName()+ " In method 1 ");

        try {
            Thread.sleep(2000);
            runnable5.method2();
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private synchronized void method2() {
        System.out.println(System.currentTimeMillis());
        System.out.println(Thread.currentThread().getName()+ " In method 2 ");
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}