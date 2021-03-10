package com.myapplication.threads;

public class SynchronizationPractice8 {

    public static void main(String[] args) throws InterruptedException {
        TempData tempData = new TempData();
        Thread thread1 = new Thread(new RunnablePractice8(tempData), "Thread 1");
        Thread thread2 = new Thread(new RunnablePractice8(tempData), "Thread 2");
        Thread thread3 = new Thread(new RunnablePractice8(tempData), "Thread 3");
        thread1.start();
        thread2.start();
        thread3.start();
        thread1.join();
        thread2.join();
        thread3.join();
        System.out.println("count is " + tempData.count);
    }
}

class TempData {
    int count = 0;
}

class RunnablePractice8 implements Runnable {

    private final TempData tempData;

    RunnablePractice8(TempData tempData) {
        this.tempData = tempData;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            synchronized (tempData) {
                increaseCount();
                try {
                    Thread.sleep(100);
                    System.out.println(Thread.currentThread().getName() + " count is " + tempData.count);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void increaseCount() {
        tempData.count++;
    }
}
