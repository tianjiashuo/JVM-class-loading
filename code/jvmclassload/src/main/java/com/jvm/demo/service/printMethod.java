package com.jvm.demo.service;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;
import com.jvm.demo.Entity.*;

public class printMethod {
    String result = "";
    AreaVo areaVo = new AreaVo();
    //MethodAreaVo methodAreaVo = new MethodAreaVo();

    public void printTrack(){
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
        HashMap<Integer,String> map= new HashMap<>();
        StackTraceElement stack[] = Thread.currentThread().getStackTrace();
        for(int i=0;i<stack.length;i++){
            String value ="ClassName: "+stack[i].getClassName()
                    +"  MethodName: "+stack[i].getMethodName()
                    +"  LineNumber: "+Thread.currentThread().getStackTrace()[i].getLineNumber();
            map.put(i,value);
        }
        return map;
//        HashMap<Integer,MethodAreaVo> map= new HashMap<>();
//        StackTraceElement stack[] = Thread.currentThread().getStackTrace();
//        for(int i=0;i<stack.length;i++){
//            methodAreaVo.setClassName(stack[i].getClassName());
//            methodAreaVo.setMethodName(stack[i].getMethodName());
//            methodAreaVo.setLineNumber(Thread.currentThread().getStackTrace()[i].getLineNumber());
//            map.put(i,methodAreaVo);
//        }
//        return map;
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
