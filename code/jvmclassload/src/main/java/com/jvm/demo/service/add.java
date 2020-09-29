package com.jvm.demo.service;

import java.util.HashMap;
import java.util.Map;

public class add {
    private int a = 1;
    String testAdd = "";
    Map<Integer,String> map = new HashMap<>();
    public int  add(int b){
//        System.out.println("-----------------");
//        System.out.println("in class add:");
        printMethod pr = new printMethod();
        //方法一
        //pr.printTrack();
        //方法二
        //Hash
        this.map=pr.printTrack2();
        //System.out.println(r.result);
        this.testAdd = pr.result;
        int c = a + b;
        return c;
    }

    public static void main(String[] args) {
        add a = new add();
        a.add(1);
    }
}
