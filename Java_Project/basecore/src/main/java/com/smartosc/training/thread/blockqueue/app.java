package com.smartosc.training.thread.blockqueue;

import java.util.Random;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.BlockingQueue;

public class app {
    private BlockingQueue<Integer> queue = new ArrayBlockingQueue<Integer>(10);

    public void producer() throws InterruptedException{
        Random random = new Random();

        while(true){
            queue.put(random.nextInt(100));
        }
    }

    public void consumer() throws InterruptedException {
        Random random = new Random();
        while (true){
            Thread.sleep(100);
            if(random.nextInt(10)==0){
                Integer value = queue.take();
                System.out.println("Value take: "+ value+"; Queue size: "+ queue.size());
            }
        }
    }

    public void producer1() throws InterruptedException{
        synchronized (this){
            System.out.println("Produce thread is running");
            wait();
            System.out.println("Resumed.");
        }
    }

    public void consumer1() throws InterruptedException {
        Scanner scanner = new Scanner(System.in);
        Thread.sleep(2000);
        synchronized (this){
            System.out.println("Waiting for return key");
            scanner.nextLine();
            System.out.println("Return key pressed.");
            notify();
            Thread.sleep(5000);
        }
    }


    public void dowork() throws InterruptedException {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    producer1();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    consumer1();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        thread.start();
        thread1.start();

        thread.join();
        thread1.join();
    }

    public static void main(String[] args) throws InterruptedException {
        app app1 = new app();
        app1.dowork();
    }
}
