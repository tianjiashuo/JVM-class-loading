package com.jvm.demo.service;

public class add {
    private int a = 1;
    String testAdd = "";
    public int  add(int b){
//        System.out.println("-----------------");
//        System.out.println("in class add:");
        printMethod pr = new printMethod();
        //方法一
        pr.printTrack();
        //方法二
        //pr.printTrack2();
        //System.out.println(pr.result);
        this.testAdd = pr.result;
        int c = a + b;
        return c;
    }
}
