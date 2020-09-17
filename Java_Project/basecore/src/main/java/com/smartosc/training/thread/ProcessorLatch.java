package com.smartosc.training.thread;

import java.util.concurrent.CountDownLatch;

public class ProcessorLatch implements Runnable{
    private CountDownLatch latch;

    public ProcessorLatch(CountDownLatch latch) {
        this.latch = latch;
    }

    @Override
    public void run() {
        System.out.println("Start. ");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        latch.countDown();
    }
}
