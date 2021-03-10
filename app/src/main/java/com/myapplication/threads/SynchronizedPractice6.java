package com.myapplication.threads;

/*
* Suppose you have 2 threads (Thread-1 on object1 and Thread-2 on object2).
*  Thread-1 is in synchronized method1(), can Thread-2 enter synchronized method2() at same time?
* ---> Yes
* */


public class SynchronizedPractice6 {
    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread(new RunnablePractice6(),"Thread 1");
        Thread thread2 = new Thread(new RunnablePractice6(),"Thread 2");
        thread1.start();
        Thread.sleep(5);
        thread2.start();
    }
}

class RunnablePractice6 implements Runnable {
    @Override
    public void run() {
        if(Thread.currentThread().getName().equals("Thread 1")){
            method1();
        } else {
            method2();
        }
    }

    private synchronized void method1() {
        System.out.println(System.currentTimeMillis());
        System.out.println(Thread.currentThread().getName() +" in Method 1");
        try {
            Thread.sleep(2000);
            System.out.println("Method 1 end");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    synchronized private void method2() {
        System.out.println(System.currentTimeMillis());
        System.out.println(Thread.currentThread().getName() +" in Method 2");
        System.out.println("Method 2 end");
    }
}