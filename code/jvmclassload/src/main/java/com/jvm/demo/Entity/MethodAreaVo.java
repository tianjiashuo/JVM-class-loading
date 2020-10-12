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
    private String classname;
    private long[] pool;
    private String[] method;
    private String[] param;
    private String value;
    private String fieldName;
}
