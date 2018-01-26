package com.huato.springbootredissentinel.service;

import com.huato.springbootredissentinel.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;


import java.io.UnsupportedEncodingException;

@Service("redisService")
@EnableCaching
public class RedisSentinelServer {
    //Spring注入一个操作字符串的StringRedisTemplate
    @Autowired
    StringRedisTemplate stringRedisTemplate;
    //Spring注入一个通用的操作Redis模板
    @Autowired
    RedisTemplate<Object,Object> redisTemplate;

    public void set(String key,String value){
        ValueOperations<String,String> valueOperations = this.stringRedisTemplate.opsForValue();
        boolean hasKey = this.stringRedisTemplate.hasKey(key);
        if(hasKey) {
            System.out.println("This key has set");
        }else {
            valueOperations.set(key,value);
        }
    }

    public String get(String key){
        return stringRedisTemplate.opsForValue().get(key);
    }

    public void del(String key) {
        stringRedisTemplate.delete(key);
    }

    public void sentinelSet(User user){
        String key = null;
        try {
            key = new String(user.getId().getBytes("gbk"),"utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        System.out.println(key);
        redisTemplate.opsForValue().set(key, user.toString());

    }

    public String sentinelGet(String key){
        return stringRedisTemplate.opsForValue().get(key);
    }


}
