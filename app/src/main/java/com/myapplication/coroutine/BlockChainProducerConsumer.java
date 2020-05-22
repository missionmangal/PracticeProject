package com.myapplication.coroutine;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

public class BlockChainProducerConsumer {


    public static void main(String[] args) {
        BlockingQueue<Integer> sharedQueue= new LinkedBlockingDeque<>();
        Thread producer =new Thread( new Producer(sharedQueue));
        Thread consumer =new Thread( new Consumer(sharedQueue));

        producer.start();
        consumer.start();

    }
    
    static class Producer implements Runnable{
        BlockingQueue<Integer> sharedQueue;
        Producer(BlockingQueue<Integer>sharedQueue){
            this.sharedQueue = sharedQueue;
        }
        @Override
        public void run() {
            produce();
        }

        private void produce() {
            for(int i=0;i<=10;i++) {
                try {
                    System.out.println("Produced:: "+i);
                    sharedQueue.put(i);
                    Thread.sleep(300);
                }catch (Exception e){
                    e.fillInStackTrace();
                }
            }
        }
    }


    static class Consumer implements Runnable{
        BlockingQueue<Integer> sharedQueue;
        Consumer(BlockingQueue<Integer>sharedQueue){
            this.sharedQueue = sharedQueue;
        }
        @Override
        public void run() {
            consume();
        }

        private void consume() {
            for(int i=0;i<=10;i++) {
                try {

                    System.out.println("Consumed:: "+sharedQueue.take());

                    Thread.sleep(300);
                }catch (Exception e){
                    e.fillInStackTrace();
                }
            }
        }
    }
    
}
