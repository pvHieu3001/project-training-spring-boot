package com.smartosc.training.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class HelloThread {
    private boolean running = true;
    private int count = 0;
    private Object lock = new Object();
    private Object lock1 = new Object();

    public void increase(){
         this.count++;
    }
    public void dowork() throws InterruptedException {
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 2000; i++){
                    increase();
                }
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 2000; i++){
                    increase();
                }
            }
        });

        t1.start();
        t2.start();
        t1.join();
        t2.join();
    }

    private Random random = new Random();
    private List<Integer> a = new ArrayList<>();
    private List<Integer> b = new ArrayList<>();

    public void stateOne(){
        synchronized(lock) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            a.add(random.nextInt(100));
        }
    }

    public void stateTwo() {
        synchronized (lock1){
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            b.add(random.nextInt(100));
        }
    }

    public void process(){
        for(int i = 0; i < 1000; i++){
            stateOne();
            stateTwo();
        }
    }

    public void runapp() throws InterruptedException {
        System.out.println("Starting...");
        long start = System.currentTimeMillis();

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                process();
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                process();
            }
        });

        t1.start();
        t2.start();
        t1.join();
        t2.join();

        long end = System.currentTimeMillis();

        System.out.println("Time take: " + (end-start));
        System.out.println("List a: " + a.size()+" List b: "+b.size());
    }

    public void shutdown(){
        System.out.println("count = "+count);
    }
}
