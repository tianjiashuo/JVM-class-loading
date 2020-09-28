package com.jvm.demo.service;

public class printMethod {
    String result = "";
    void printTrack(){
//        System.out.println("方法1");
//        System.out.println("id");
//        System.out.println(Thread.currentThread().getId());
        StackTraceElement[] st = Thread.currentThread().getStackTrace();
        if(st==null){
            System.out.println("无堆栈...");
            return;
        }
        StringBuffer sbf =new StringBuffer();
        for(StackTraceElement e:st){
            if(sbf.length()>0){
                sbf.append(" <- ");
                sbf.append(System.getProperty("line.separator"));
            }
            sbf.append(java.text.MessageFormat.format("{0}.{1}() {2}"
                    ,e.getClassName()
                    ,e.getMethodName()
                    ,e.getLineNumber()));
        }
        //System.out.println(sbf.toString());
        this.result = sbf.toString();
    }
}
