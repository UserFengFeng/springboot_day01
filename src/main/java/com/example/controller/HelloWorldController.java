package com.example.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "测试相关")
@RestController
public class HelloWorldController {
    @ApiOperation("测试接口")
    @RequestMapping("/hello")
    public String hello() {
        return "hello world";
    }
}
