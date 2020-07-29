package com.smartosc.training.pg1;

/**
 * Fresher-Training
 *
 * @author Hieupv
 * @created_at 27/07/2020 - 2:17 PM
 * @created_by Hieupv
 * @since 27/07/2020
 */
public class Branch1 extends Base {
    int y;
    public Branch1(int y){
        this.y = y;
    }
    public Branch1(){
    }
    public Branch1(int x, int y){
        super(x);
    }
    public void resole(){
        System.out.println("method of Br1");
    }

    public static void main(String[] args) {
        Base br = new Branch1();
        System.out.println(br.c+ br.d+br.x);
    }
}
