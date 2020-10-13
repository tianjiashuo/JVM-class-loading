package com.jvm.demo.service;

import java.util.Map;

public class test {
    public Map<Integer,String> testAdd(){
        add ad = new add();
        int result = ad.add(2);
        printMethod pr = new printMethod();
        System.out.println(ad.map.size());
        return pr.removeUnusedMethod(ad.map);
    }
}
