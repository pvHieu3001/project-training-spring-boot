package com.smartosc.training.run;


import com.smartosc.training.pg1.*;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Fresher-Training
 *
 * @author Hieupv
 * @created_at 24/07/2020 - 3:49 PM
 * @created_by Hieupv
 * @since 24/07/2020
 */
public class Run {
    static int st;
    public void func1() throws Exception {
        System.out.println("fn1");
        throw new Exception();
    }

    public void func2() throws RuntimeException {
        System.out.println("fn2");
        throw new RuntimeException();
    }

    public void func3() throws IOException {
        throw new IOException();
    }

    public static boolean isTrue(int x) {
        return x-- > 0 ? true : false;
    }

    public static void main(String[] args) throws Exception {
        int _a = 2;
        int $_;
        double d = 100;
        float f = 200;

        d = _a;
        d = f;

        String[] strs = new String[2];
        int id = 0;

        System.out.println(strs[id].concat("haha"));

//        for(String s : strs){
//            strs[id].concat("idx: "+id);
//            id++;
//        }
//
//        for(String s : strs){
//            System.out.println(s);
//        }


//        String s = " ";
//        s.trim();
//        //s.concat("haha");
//        System.out.println(s);

//        System.out.println("5+2= "+3+4);
//        System.out.println("5+2= "+(3+4));
//        ArrayList mylist = new ArrayList();
//        String[] myArr;
//
//        try{
//            while (true){
//                mylist.add("String");
//            }
//        }catch (RuntimeException r){
//            System.out.println("Runtime");
//        }
//        catch (Exception e){
//            System.out.println("Exception");
//        }


//        Base b1 = new Branch2();
//        Base b2 = new Branch1();
//        Base b3 = new Branch2();
//
//        b1 = (Base)b3;
//        Base b4 = (Branch1)b3;
//
//        Branch1 br1 = new Branch2();
//        br1.resole();

        //b1.resole();
        //b4.resole();



//        LocalDate date1 = LocalDate.now();
//        LocalDate date2 = LocalDate.of(2000, 2, 1);
//        LocalDate date3 = LocalDate.parse("2000-06-20", DateTimeFormatter.ISO_DATE);
//
//        System.out.println(date1+"---"+date2+"---"+date3+"---");
//        A itA = new A();
//        Run r = new Run();
//        r.func1();
//        r.func2();


//        r.st = 2;
//        Run r1 = new Run();
//        r1.st = 3;

//        System.out.println(args[0]);


//        String [][] chs = new String[2][];
//        chs[0] = new String[2];
//        chs[1] = new String[5];
//        int a = 97;
//
//        for(int i = 0 ; i < chs.length; i++){
//            for(int j = 0 ; j < chs.length; j++){
//                chs[i][j] = ""+a;
//                a++;
//            }
//        }
//
//        for(String[] t : chs){
//            for(String s: t){
//                System.out.println(s);
//            }
//        }





//        int x= 5;
//        while (isTrue(x)){
//            System.out.println(x);
//            x--;
//        }


//        String a = "a";
//        long d = 1_2_3_4_0;
//        int i;
//        LocalTime lc = LocalTime.of(1, 11, 2);

//        b.insert(0, "adad");
//        a.concat("haha");
//        System.out.println(a);
//        System.out.println(b);
//        System.out.println(d);

//        boolean b1 = true;                          can't use boolean
//        switch (b1) {
//            case true:
//                System.out.println("shut");
//        }

//        List<B> aList = new ArrayList<>();
//        aList.add(new D() {
//            @Override
//            public void set() {
//
//            }
//
//            @Override
//            public void get() {
//
//            }
//        });

//        F f = new F("moto");
//        f.resole();

//        r.func3();
//
//        StringBuilder b = new StringBuilder("kashdk");
//        String s = "";
//        System.out.println(b.toString().equals(s));
    }
}
