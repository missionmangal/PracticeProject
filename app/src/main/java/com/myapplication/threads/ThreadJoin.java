package com.myapplication.threads;

public class ThreadJoin {

    public static void main(String[] args) {
        Thread thread1 = new Thread(new Thread1(), "Thread 111");
        Thread thread2 = new Thread(new Thread2(), "Thread 333");
        Thread thread3 = new Thread(new Thread3(), "Thread 999");
        try {
            thread1.start();
            thread1.join();
            thread2.start();
            thread2.join();
            thread3.start();
            thread3.join();
        } catch (InterruptedException ignored) {

        }
        System.out.println("Main Thread Ended");
    }

}

class Thread1 implements Runnable {
    @Override
    public void run() {
        int n = 10;
        for (int i = 0; i < n; i++) {
            System.out.println(Thread.currentThread().getName() + " " + i);
            try {
                Thread.sleep(500);
            } catch (InterruptedException ignored) {

            }
        }
    }
}

class Thread2 implements Runnable {
    @Override
    public void run() {
        int n = 10;
        for (int i = 0; i < n; i++) {
            System.out.println(Thread.currentThread().getName() + " " + i);
            try {
                Thread.sleep(500);
            } catch (InterruptedException ignored) {

            }
        }
    }
}

class Thread3 implements Runnable {
    @Override
    public void run() {
        int n = 10;
        for (int i = 0; i < n; i++) {
            System.out.println(Thread.currentThread().getName() + " " + i);
            try {
                Thread.sleep(500);
            } catch (InterruptedException ignored) {

            }
        }
    }
}

