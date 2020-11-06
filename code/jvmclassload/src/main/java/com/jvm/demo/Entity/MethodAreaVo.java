package com.jvm.demo.Entity;

import lombok.*;

@Data
@Builder
@EqualsAndHashCode
/*1. class的全限定名
* 2. class的常量池
* 3. Field的信息
* 4. 方法信息
* 5. static变量信息*/
public class MethodAreaVo extends AreaVo{
    private String area = "MethodArea";
    private String type;
    private String ClassName;
    private long[] pool;
    private String[] method;
    private String[] param;
    private String value;
    private String MethodName;
    private int LineNumber;
//    public MethodAreaVo(){}
//    public void setClassName(String className){this.ClassName = className;}
//    public String getClassName(){return this.ClassName;}
//    public void setMethodName(String methodName){this.MethodName = methodName;}
//    public String getMethodName(){return this.MethodName;}
//    public void setLineNumber(int lineNumber){this.LineNumber = lineNumber;}
//    public int getLineNumber(){return this.LineNumber;}
}
