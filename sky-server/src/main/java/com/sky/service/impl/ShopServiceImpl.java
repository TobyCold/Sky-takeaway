package com.sky.service.impl;

import com.sky.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

@Service
//@Scope("prototype")
public class ShopServiceImpl implements ShopService {
    @Autowired
    RedisTemplate redisTemplate;

    /**
     * 获取店铺状态
     * @return
     */
    public Integer getStatus(){
        ValueOperations valueOperations = redisTemplate.opsForValue();
        Integer status = (Integer) valueOperations.get("status");
        return status;
    }

    /**
     * 设置店铺状态
     * @param status
     */
    public void setStatus(Integer status){
        ValueOperations valueOperations = redisTemplate.opsForValue();
        valueOperations.set("status", status);
    }
}
