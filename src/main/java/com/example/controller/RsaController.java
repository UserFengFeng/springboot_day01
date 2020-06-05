package com.example.controller;

import com.example.service.RsaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class RsaController extends BaseController{

    @Autowired
    private RsaService rsaService;

    //  获取公钥
    @RequestMapping("/getPublicKey")
    public Map<String, Object> getPublickey() {
        return successResult(rsaService.getPublicKey());
    }

}
