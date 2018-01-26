package com.huato.springbootredissentinel;

import com.huato.springbootredissentinel.service.RedisSentinelServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/web")
public class WebController {
    @Autowired
    private RedisSentinelServer redisComponet;

    @RequestMapping(value="/set/{key}/{value}")
    public String set(@PathVariable String key, @PathVariable String value){
        redisComponet.set(key, value);
        return "set key success!";
    }

    @RequestMapping(value="/get/{key}")
    public String get(@PathVariable String key){
        return redisComponet.get(key);
    }

    @RequestMapping(value="/del/{key}")
    public void del(@PathVariable String key){
        redisComponet.del(key);
    }
}
