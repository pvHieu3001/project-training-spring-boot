package com.smartosc.training.thread.examplelowlever;

import java.util.LinkedList;

public class ProcessorExam {
    private LinkedList<Integer> list = new LinkedList<Integer>();
    private final int limit = 10;
    private Object lock = new Object();

    public void producer() throws InterruptedException {
        int value = 0;
        while(true){
            synchronized (lock){
                while (list.size()==limit){
                    lock.wait();
                }
                list.add(value++);
                lock.notify();
            }
        }
    }

    public void consumer() throws InterruptedException {
        while (true){
            synchronized (lock){
                while (list.size()==9){
                    lock.wait();
                }
                System.out.print("List size is: "+list.size());
                int value = list.removeFirst();
                System.out.println("; value is: "+value);
                lock.notify();
            }
            Thread.sleep(102);
        }
    }
}
