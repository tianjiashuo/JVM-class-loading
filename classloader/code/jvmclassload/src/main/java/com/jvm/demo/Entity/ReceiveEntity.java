package com.jvm.demo.Entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author qz
 */
@Data
@Builder
@TableName("receive")
public class ReceiveEntity {
    @TableId
    private int id;

    @NotBlank(message = "文件名不能为空")
    private String fileName;

    private String content;

    @TableField("vclass")
    private String vclass;

    @TableField("cclass")
    private String cclass;
}
