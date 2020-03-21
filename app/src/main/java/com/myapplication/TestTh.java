package com.myapplication;

public class TestTh {
    public static void main(String[] args) throws InterruptedException {

        Thread t1 = new Thread(new Thread1());
        Thread t2 = new Thread(new Thread2());
        Thread t3 = new Thread(new Thread3());
        t1.start();

        t1.join();
        t2.start();
        t2.join();
        t3.start();
    }
}


class Thread1 implements Runnable {

    @Override
    public void run() {
        for(int i = 0 ;i<5;i++){
            System.out.println(Thread.currentThread() +" : "+ i );
        }
    }
}
class Thread2 implements Runnable {

    @Override
    public void run() {
        for(int i = 0 ;i<5;i++){
            System.out.println(Thread.currentThread() +" : "+ i );
        }
    }
}
class Thread3 implements Runnable {

    @Override
    public void run() {
        for(int i = 0 ;i<5;i++){
            System.out.println(Thread.currentThread() +" : "+ i );
        }
    }
}

