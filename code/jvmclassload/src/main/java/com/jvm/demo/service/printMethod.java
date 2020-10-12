package com.jvm.demo.service;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class printMethod {
    String result = "";
    public void printTrack(){
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

    public Map<Integer,String> printTrack2(){
        //System.out.println("方法2");
        HashMap<Integer,String> map= new HashMap<>();
        StackTraceElement stack[] = Thread.currentThread().getStackTrace();
        for(int i=0;i<stack.length;i++){
            //System.out.print(stack[i].getClassName()+" ."+stack[i].getMethodName()+"-----");
            String value = stack[i].getClassName()+" ."+stack[i].getMethodName();
            map.put(i,value);
        }
        return map;
    }

    public Map<Integer,String> removeUnusedMethod(Map<Integer,String> map){
        String removeMethod1 = ".*org.*";
        String removeMethod2 = ".*invoke.*";
        String removeMethod3 = ".*http.*";
        String removeMethod4 = ".*Thread.*";
        int size = map.size();
        for(int i=0;i<size;i++){
            boolean ismatch = Pattern.matches(removeMethod1,map.get(i))
                    ||Pattern.matches(removeMethod2,map.get(i))
                    ||Pattern.matches(removeMethod3,map.get(i))
                    ||Pattern.matches(removeMethod4,map.get(i));
            if(ismatch){
                System.out.println(map.remove(i));
            }
        }
        return map;
    }

}
