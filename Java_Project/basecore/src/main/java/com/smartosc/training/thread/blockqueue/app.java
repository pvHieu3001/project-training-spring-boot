package com.smartosc.training.thread.blockqueue;

import com.smartosc.training.thread.examplelowlever.ProcessorExam;

import java.util.Random;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

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
        ProcessorExam processorExam = new ProcessorExam();
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    secondThread();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    firstThread();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        thread.start();
        thread1.start();

//        thread.join();
//        thread1.join();
    }

    private int  count = 0;
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    public void increase(){
        for(int i = 0; i < 2000; i ++){
            count++;
        }
    }

    public void firstThread()throws InterruptedException{
        lock.lock();
        System.out.println("Waiting");
        condition.await();
        System.out.println("Woken up");
        try {
            increase();
        }finally {
            lock.unlock();
        }
    }

    public void secondThread()throws InterruptedException{
        Thread.sleep(2000);
        lock.lock();
        System.out.print("Press retur key");
        new Scanner(System.in).nextLine();
        System.out.println("Return key pressed");

        condition.signal();
        try {
            increase();
        }finally {
            lock.unlock();
        }
    }

    public void finish(){
        System.out.println("Count: " + count);
    }


    public static void main(String[] args) throws InterruptedException {
        app app1 = new app();
        app1.dowork();
        app1.finish();
    }
}
