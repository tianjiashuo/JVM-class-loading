package com.jvm.demo.Entity;

import lombok.*;

import java.util.ArrayList;

@Data
@Builder
@EqualsAndHashCode
/*JvmStack里面存的是
* 1. 对象引用
* 2. 方法引用和方法的参数
* 3. 对象类型*/
public class JvmStackVo extends AreaVo{
    private String area = "JvmStack";
//    private String referenceName;
//    private String type;
//    private String methodName;
//    private String[] innerMethodName;
    private String methodName;
    private ArrayList chain;

}
