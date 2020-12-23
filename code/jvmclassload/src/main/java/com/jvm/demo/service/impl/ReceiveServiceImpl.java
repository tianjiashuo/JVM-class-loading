package com.jvm.demo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jvm.demo.Entity.ReceiveEntity;
import com.jvm.demo.dao.ReceiveDao;
import com.jvm.demo.service.ReceiveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author qz
 */
@Service("ReceiveService")
public class ReceiveServiceImpl extends ServiceImpl<ReceiveDao,ReceiveEntity> implements ReceiveService{
    @Autowired
    private ReceiveService receiveService;

    @Override
    public ReceiveEntity receiveById(int id) {
        return receiveService.getById(id);
    }
}
