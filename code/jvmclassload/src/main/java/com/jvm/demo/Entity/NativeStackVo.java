package com.jvm.demo.Entity;

import lombok.*;

@Data
@Builder
@EqualsAndHashCode
public class NativeStackVo extends AreaVo{
    private String area = "NativeStack";
    private String variableName;
    private String type;
    private String value;
    private String quote;
}
