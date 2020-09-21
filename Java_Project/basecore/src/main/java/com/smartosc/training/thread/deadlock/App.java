package com.smartosc.training.thread.deadlock;

import java.util.Arrays;

public class App {
    private Runner runner = new Runner();
    public void runapp() throws InterruptedException {
        System.out.println("Starting...");
        long start = System.currentTimeMillis();

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    runner.firstThread();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    runner.secondThread();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        t1.start();
        t2.start();
        t1.join();
        t2.join();

        long end = System.currentTimeMillis();

        System.out.println("Time take: " + (end-start));
        runner.finish();
    }

    public static void main(String[] a) throws InterruptedException {
//        App app = new App();
//        app.runapp();

//        String[] string = {"N", "n", "L", "O", "S"};
//        Arrays.sort(string);
//        for(String s : string){
//            System.out.println(s);
//        }
//
//       Integer [] a = new Integer[2];
//        a[1] = 10;

//        for(Integer i : a)  {
//            System.out.println(i);
//        }

//        int array[][][]  = new int[1][2][3];
//        array[0][0][0] = 8;
//        array[0][0][2] = 12;
//        array[0][0][1] = 10;
//        array[0][1][0] = 40;
//        array[0][1][2] = 11;
//        array[0][1][1] = 21;
//
//        System.out.println(Arrays.stream(Arrays.stream(array).toArray()).toArray());
//        int array1[][][] = {{{8,10,12},{40,21,11}}};
//        System.out.println(array.equals(array1));
//
////        for (int[][] ints : array) {
////            System.out.println(ints);
////            for (int[] anInt : ints) {
////                System.out.println(anInt);
////            }
////        }
//
//        array[0][0][1] = 110;
//        for(int[][] aa : array1) {
//            for(int[] bb : aa) {
//                System.out.print("{");
//                for(int cc : bb) {
//                        System.out.print(cc + ", ");
//                    }
//                }
//                System.out.print("}");
//                System.out.println();
//            }
//        }

//        do{
//            System.out.println("hahah");
//        }while(false);
//
//        StringBuilder sb = new StringBuilder("iZo");
//        StringBuilder sbo = new StringBuilder("iZo");
//
//        String s = new String("iZo");
////        System.out.println(s==sb);
//        System.out.println(sb==sbo);
//        System.out.println(sb.equals(sbo));
//        System.out.println(s.equals(sb));
//        System.out.println(sb.toString().equals(sbo.toString()));
//        System.out.println(sb);
//        System.out.println(sbo);

//        Long l = 3L;
//        Double d = 10.0;
//        Float f = 1.3F;
//        Float f1 = 4F;
//
//        Double sh = 1.0/0.0;
//        Double sh2 = 0.0/0.0;
//        System.out.println(sh.isInfinite());
//        System.out.println(sh2);
    }
}
