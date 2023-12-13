package com.sky.service;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

//@SpringBootTest
@Slf4j
public class RedisTest {

    @Autowired
     RedisTemplate redisTemplate;
    @Test
    public void setRedis(){
        ValueOperations valueOperations = redisTemplate.opsForValue();

        valueOperations.set("name", "小明");

        System.out.println(valueOperations.get("name"));
    }
}
