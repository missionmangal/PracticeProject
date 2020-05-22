package com.myapplication.coroutine;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ProducerConsumerProblem {

    List<String> productList = null;
    int buffer = 5;
    int count = 0;
    boolean isBreak =false;

    public static void main(String[] args) throws InterruptedException {
        List<String> products = new ArrayList<>();
        final ProducerConsumerProblem problem = new ProducerConsumerProblem();
        Thread producer = new Thread(problem.new ProducerThread(products));
        Thread consumer = new Thread(problem.new ConsumerThread(products));

        producer.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.fillInStackTrace();
        }
        consumer.start();

        producer.join();
        consumer.join();
    }



    void produceProduct(){
        if (productList == null) {
            productList = new ArrayList<>();
        }
        while (true) {
            synchronized (this) {
                if (productList.size() == buffer) {
                    try {
                        if(isBreak)
                            break;
                        System.out.println("******************Producer waiting********************* :: ");
                        productList.wait();
                    } catch (InterruptedException e) {
                        System.out.println("******************Producer waiting********************* :: " + e.getMessage());
                        e.printStackTrace();
                    }
                }
//                    else

                if (count < 40) {
                    String str = "product :: " + ++count;
                    System.out.println("Producer produced" + str);
                    try {
                        Thread.sleep(300);
                    } catch (InterruptedException e) {
                        e.fillInStackTrace();
                    }
                    productList.add(str);
                    productList.notify();
                } else {
                    System.out.println("*****************Producer break*****************" );
                    isBreak = true;
                    productList.notify();
                    break;
                }

            }
        }
    }

    void consumeProduct() {

        while (true) {
            synchronized (this) {
                if (productList.size() < 1) {
                    try {
                        if(isBreak){
                            System.out.println("*****************Consumer  break***************:" );
                            break;
                        }
                        System.out.println("******************Consumer waiting********************* :: ");
                        productList.wait();
                    } catch (InterruptedException e) {
                        System.out.println("******************Consumer waiting********************* :: " + e.getMessage());
                        e.fillInStackTrace();

                    }
                }

                if (count < 41) {
                    if(productList.size()>0) {
                        String str = productList.get(productList.size() - 1);
                        System.out.println("Consumer consumed :: " + str);
                        productList.remove(productList.size() - 1);
                        productList.notify();
                        try {
                            Thread.sleep(600);
                        } catch (InterruptedException e) {
                            e.fillInStackTrace();
                        }
                    }
                } else {
                    productList.notify();
                    isBreak =true;
                    System.out.println("*****************Consumer  break***************:" );
                    break;

                }

            }
        }
    }
    class ConsumerThread implements Runnable {
//        List<String>productList = null;
        ConsumerThread(List<String>productList){
//            this.productList = productList;
        }
        @Override
        public void run() {
             consumeProduct();
        }

        void consumeProduct() {

            while (true) {
                synchronized (productList) {
                    if (productList==null||productList.size() < 1) {
                        try {
                            if(isBreak){
                                System.out.println("*****************Consumer  break***************:" );
                                break;
                            }
                            System.out.println("******************Consumer waiting********************* :: ");
                            productList.wait();
                        } catch (InterruptedException e) {
                            System.out.println("******************Consumer waiting********************* :: " + e.getMessage());
                            e.fillInStackTrace();

                        }
                    }

                    if (count < 41) {
                        if(productList.size()>0) {
                            String str = productList.get(productList.size() - 1);
                            System.out.println("Consumer consumed :: " + str);
                            productList.remove(productList.size() - 1);
                            productList.notify();
                            try {
                                Thread.sleep(600);
                            } catch (InterruptedException e) {
                                e.fillInStackTrace();
                            }
                        }
                    } else {
                        productList.notify();
                        isBreak =true;
                        System.out.println("*****************Consumer  break***************:" );
                        break;

                    }

                }
            }
        }
    }

    class ProducerThread implements Runnable {
//        List<String>productList = null;
        ProducerThread(List<String>productList){
//            this.productList = productList;
        }
        @Override
        public void run() {
            produceProduct();
        }
        void produceProduct(){
            if (productList == null) {
                productList = new ArrayList<>();
            }
            while (true) {
                synchronized (productList) {
                    if (productList.size() == buffer) {
                        try {
                            if(isBreak)
                                break;
                            System.out.println("******************Producer waiting********************* :: ");
                            productList.wait();
                        } catch (InterruptedException e) {
                            System.out.println("******************Producer waiting********************* :: " + e.getMessage());
                            e.printStackTrace();
                        }
                    }
//                    else

                    if (count < 40) {
                        String str = "product :: " + ++count;
                        System.out.println("Producer produced" + str);
                        try {
                            Thread.sleep(300);
                        } catch (InterruptedException e) {
                            e.fillInStackTrace();
                        }
                        productList.add(str);
                        productList.notify();
                    } else {
                        System.out.println("*****************Producer break*****************" );
                        isBreak = true;
                        productList.notify();
                        break;
                    }

                }
            }
        }
    }

}


