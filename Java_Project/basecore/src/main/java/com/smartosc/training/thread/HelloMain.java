package com.smartosc.training.thread;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class HelloMain {
    public static void main(String[] args) throws InterruptedException {

        //new Thread1().start();
        //new Thread2().start();

//        HelloThread helloThread = new HelloThread();
//
//        helloThread.dowork();
//        helloThread.shutdown();
//
//        helloThread.runapp();
//
//        //Thread pools
//
//        ExecutorService executorService = Executors.newFixedThreadPool(5);
//
//        ProcessorThreadPool processor = new ProcessorThreadPool(1);
//
//        for(int i = 0; i < 5; i++){
//            executorService.submit(new ProcessorThreadPool(i));
//        }
//
//        executorService.shutdown();
//        System.out.println("All tasks submitted. ");
//
//        executorService.awaitTermination(1, TimeUnit.DAYS);
//
//        System.out.println("All tasks completed.");
//
//
//        //Thread countdown latch
//
//        ExecutorService executorService1 = Executors.newFixedThreadPool(1);
//        CountDownLatch countDownLatch = new CountDownLatch(3);
//        long start = System.currentTimeMillis();
//        for(int i = 0; i < 3; i++){
//            executorService1.submit(new ProcessorLatch(countDownLatch));
//        }
//
//        countDownLatch.await();
//        long end = System.currentTimeMillis();
//        System.out.println("Completed in "+ (end-start));

        System.out.println(true | false ^ true);
        System.out.println((true | false) ^ true);
        System.out.println(true ^ false ^ !true);
    }
}
