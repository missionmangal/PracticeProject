package com.myapplication.threads;

/*Suppose you have 2 threads (Thread-1 and Thread-2) on same object.
 Thread-1 is in synchronized method1(), can Thread-2 enter synchronized method2() at same time?*/
public class SynchronizationPractice1 {

    public static void main(String[] args) throws InterruptedException {
        ProductData productData = new ProductData("Gold", "Metal");
        ThreadTemp1 threadTemp1 = new ThreadTemp1(productData);
        Thread thread1 = new Thread(threadTemp1, "Thread 111");
        Thread thread2 = new Thread(threadTemp1, "Thread 333");
        thread1.start();
        Thread.sleep(10);
        thread2.start();
    }
}

class ProductData {
    String name;
    String category;

    ProductData(String name, String category) {
        this.name = name;
        this.category = category;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    void printName() {
        System.out.println(System.currentTimeMillis());
        System.out.println(Thread.currentThread().getName() + " name " + name);
    }

    void printCategory() {
        System.out.println(System.currentTimeMillis());
        System.out.println(Thread.currentThread().getName() + " category " + category);
    }
}

class ThreadTemp1 implements Runnable {

    final ProductData productData;

    ThreadTemp1(ProductData productData) {
        this.productData = productData;
    }

    @Override
    public void run() {
        if (Thread.currentThread().getName().equals("Thread 111"))
            method1();
        else
            method2();
    }

    synchronized void method1() {
        productData.printName();
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    synchronized void method2() {
        productData.printCategory();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
