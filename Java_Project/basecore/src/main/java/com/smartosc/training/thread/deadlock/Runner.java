package com.smartosc.training.thread.deadlock;

import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Runner {
    private Account acc1 = new Account();
    private Account acc2 = new Account();
    private Lock lock1 = new ReentrantLock();
    private Lock lock2 = new ReentrantLock();

    private void acquireLock(Lock firstlock, Lock secondLock) throws InterruptedException {
        boolean gotFirstLock = false;
        boolean gotSecondLock = false;

        while(true){
            try{
                gotFirstLock = firstlock.tryLock();
                gotSecondLock = secondLock.tryLock();
            }finally {
                if(gotFirstLock && gotSecondLock){
                    return;
                }

                if(gotFirstLock){
                    firstlock.unlock();
                }

                if(gotSecondLock){
                    secondLock.unlock();
                }
            }

            Thread.sleep(2);
        }
    }
    public void firstThread()throws InterruptedException{
        Random random = new Random();
        for(int i = 0; i < 10000; i++){
            acquireLock(lock1, lock2);
            try {
                Account.tranfer(acc1, acc2, random.nextInt(100));
            }finally {
                lock2.unlock();
                lock1.unlock();
            }
        }
    }

    public void secondThread()throws InterruptedException{
        Random random = new Random();
        for(int i = 0; i < 10000; i++){
            acquireLock(lock1, lock2);
            try {
                Account.tranfer(acc2, acc1, random.nextInt(100));
            }finally {
                lock2.unlock();
                lock1.unlock();
            }
        }
    }

    public void finish(){
        System.out.println("Account 1 has banlance: " + acc1.getBalance());
        System.out.println("Account 2 has banlance: " + acc2.getBalance());
        System.out.println("Total banlance: " + (acc2.getBalance()+acc1.getBalance()));
    }
}
