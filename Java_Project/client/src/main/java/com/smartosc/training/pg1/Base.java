package com.smartosc.training.pg1;

/**
 * Fresher-Training
 *
 * @author Hieupv
 * @created_at 27/07/2020 - 2:09 PM
 * @created_by Hieupv
 * @since 27/07/2020
 */
public class Base {
    int x;
    private int b;
    protected int c;
    public int d;
    public Base(){
    };
    public Base(int x){
        this.x = x;
    };
    public void resole(){
        System.out.println("method of Base class");
    }
}
