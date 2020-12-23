package com.jvm.demo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jvm.demo.Entity.ReceiveEntity;

public interface ReceiveService extends IService<ReceiveEntity> {
    ReceiveEntity receiveById(int id);
}
