package com.myapplication;

import java.util.ArrayList;

public class ProducerConsumer {
      ArrayList<String> products;
      int count=  0;

    public ArrayList<String> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<String> products) {
        this.products = products;
    }

    public static void main(String[] args) {
        System.out.println("Producer start-");
        ProducerConsumer pc = new ProducerConsumer();
        pc.setProducts(new ArrayList<String>());
        Producer producer = new Producer(pc);
        Consumer consumer = new Consumer(pc);
        Thread t1 = new Thread(producer);
        Thread t2 = new Thread(consumer);
        t1.start();
        try {
            Thread.sleep(200);
        }catch (Exception e){

        }
        t2.start();
    }
}

class Producer implements Runnable {

    ProducerConsumer obj;
    Producer(ProducerConsumer obj){
        this.obj = obj;
    }

    @Override
    public void run() {

        produce();
    }

    private  void produce(){
        while (true) {
            synchronized(obj) {
                try {
                    while (obj.getProducts().size() >= 4) {
                        wait();
                    }


                    obj.count++;
                    System.out.println("Producer produced-"
                            + obj.count);
                    obj.getProducts().add("Product Added");
                    notify();
                    Thread.sleep(1000);
                } catch (Exception e) {
                    e.fillInStackTrace();
                }
            }
        }
    }
}

class Consumer implements Runnable {

    ProducerConsumer obj;
    Consumer(ProducerConsumer obj){
        this.obj = obj;
    }
    @Override
    public void run() {


        consume();
    }
    private synchronized void consume(){

            while (true) {
                synchronized(obj) {
                try {
                    while (obj.getProducts().size() < 1) {

                        notify();
                        wait();
                    }
                    System.out.println("Consume produced-"
                            + obj.count);
                    obj.count--;
                    obj.getProducts().remove(obj.getProducts().size() - 1);
                    if (obj.getProducts().size() < 2) {
//                        notify();


                    }
                    Thread.sleep(1000);
                } catch (Exception e) {
                    e.fillInStackTrace();
                }
            }
        }
    }
}


