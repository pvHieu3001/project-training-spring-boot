package com.smartosc.training.thread;

public class HelloMain {
    public static class Thread1 extends Thread {
        public void run() {
            System.out.println("A");System.out.println("B");
        }
    }
    public static class Thread2 extends Thread {
        public void run() {
            System.out.println("1");
            System.out.println("2");
        }
    }
    public static void main(String[] args) throws InterruptedException {

        new Thread1().start();
        new Thread2().start();

        HelloThread helloThread = new HelloThread();

        helloThread.start();

    }
}
