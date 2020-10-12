package com.jvm.demo.Entity;

import lombok.*;

@Data
@Builder
@EqualsAndHashCode
public class PcVo extends AreaVo{
    private Integer lineNumber;
    private String value;
}
