package com.smartosc.training.thread;

public class ProcessorThreadPool implements Runnable{
    private int id;

    public ProcessorThreadPool(int id){
        this.id = id;
    }

    @Override
    public void run() {
        System.out.println("Starting: " + id);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Completed: " + id);
    }
}
