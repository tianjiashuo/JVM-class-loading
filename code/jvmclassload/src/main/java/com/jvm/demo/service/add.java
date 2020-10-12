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
        this.map=pr.printTrack2();
        //System.out.println(pr.result);
        this.testAdd = pr.result;
        int c = a + b;
        return c;
    }
}
