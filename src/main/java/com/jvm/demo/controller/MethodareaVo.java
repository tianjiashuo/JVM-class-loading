package com.jvm.demo.controller;

public class MethodareaVo {
    private String classname;
    private String method;
    MethodareaVo(String classname,String method){
        this.classname = classname;
        this.method = method;
    }
    public String getClassname(){
        return this.classname;
    }
    public String getMethod(){
        return this.method;
    }
    public void setClassname(String classname){
        this.classname = classname;
    }
    public void setMethod(String method){
        this.method = method;
    }
}
