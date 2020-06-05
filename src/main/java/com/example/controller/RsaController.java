package com.example.controller;

import com.example.service.RsaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
@Api(tags = "加密相关")
public class RsaController extends BaseController{

    @Autowired
    private RsaService rsaService;

    @ApiOperation("获取公钥")
    @GetMapping("/getPublicKey")
    public Map<String, Object> getPublickey() {
        return successResult(rsaService.getPublicKey());
    }

}
