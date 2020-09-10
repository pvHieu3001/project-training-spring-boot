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

        int idx = 1;

        for (int i = 0; i < 2; i++) {

            System.out.println("Main thread running " + idx++);
            // Ngủ 2101 milli giây.
            Thread.sleep(2101);
        }

        HelloThread helloThread = new HelloThread();

        // Chạy thread
        helloThread.start();

        for (int i = 0; i < 3; i++) {
            System.out.println("Main thread running " + idx++);
            // Ngủ 2101 milli giây.
            Thread.sleep(2101);
        }

        System.out.println("==> Main thread stopped");
    }
}
