package com.myapplication.threads;

public class VolatileDemo {
     int count = 1;

    public static void main(String[] args) throws InterruptedException {
        VolatileDemo demo = new VolatileDemo();
        Runnable runnable1 = new VolatileRunnable(demo);
        Runnable runnable2 = new VolatileRunnable(demo);
        Thread thread1 = new Thread(runnable1, "Thread 1");
        Thread thread2 = new Thread(runnable2, "Thread 2");
        thread1.start();
//        Thread.sleep(10);
        thread2.start();
    }
}

class VolatileRunnable implements Runnable {
    volatile VolatileDemo volatileDemo;

    VolatileRunnable(VolatileDemo volatileDemo) {
        this.volatileDemo = volatileDemo;
    }

    @Override
    public void run() {
        increase();
    }

    private void increase() {
        for (int i = 0; i < 10; i++) {
            System.out.println(Thread.currentThread().getName() + " count " + volatileDemo.count);
            try {
                volatileDemo.count += 1;
                if(Thread.currentThread().getName().equals("Thread 1") && i == 2)
                    Thread.sleep(5);
                else
                    Thread.sleep(5);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}