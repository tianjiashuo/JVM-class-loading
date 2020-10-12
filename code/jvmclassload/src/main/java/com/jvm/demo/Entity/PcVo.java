package com.jvm.demo.Entity;

import lombok.*;

@Data
@Builder
@EqualsAndHashCode
public class PcVo extends AreaVo{
    private String area = "Pc";
    private Integer lineNumber;
    private String value;
}
