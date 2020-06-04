package com.example.demo;

import com.example.service.RedisService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashMap;
import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class MyTest {
    @Autowired
    private RedisService redisService;

    @Test
    public void hashTest() {
        Map<String, String> map = new HashMap<>();
        map.put("a879", "1");
        map.put("2131", "23");
        //redis中添加hash
        redisService.setHash("ccc", map);
        //多次获取hash
        Map hash = redisService.getHash("ccc");
        redisService.getHash("ccc");
        redisService.getHash("ccc");
        if (hash != null) {
            System.out.println(hash.toString());
        }
    }
}
