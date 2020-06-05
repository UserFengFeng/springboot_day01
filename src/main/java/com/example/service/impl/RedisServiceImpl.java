package com.example.service.impl;

import com.example.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class RedisServiceImpl implements RedisService {
    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public void setHash(String hashName, Map<String, String> map) {
        redisTemplate.opsForHash().putAll(hashName, map);
    }

    @Override
    public Map<Object, Object> getHash(String hashName) {
        if (redisTemplate.hasKey(hashName)) {
            return redisTemplate.opsForHash().entries(hashName);
        } else {
            return null;
        }
    }
}
