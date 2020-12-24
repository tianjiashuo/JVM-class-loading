package com.jvm.demo.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jvm.demo.Entity.ReceiveEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ReceiveDao extends BaseMapper<ReceiveEntity> {
    /**
     * 查询文件
     */
    public ReceiveEntity receiveById(int id);

//    /**
//     * 根据filename查询文件
//     */
//    public File
}
