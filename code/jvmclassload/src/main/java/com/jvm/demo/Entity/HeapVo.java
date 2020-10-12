package com.jvm.demo.Entity;

import lombok.*;

@Data
@Builder
@EqualsAndHashCode
public class HeapVo extends AreaVo{
    private String name;
    private String value;
    private String type;
}
