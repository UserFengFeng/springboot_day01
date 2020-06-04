package com.example.demo;

import com.example.service.RedisService;
import com.example.utils.RSAUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Base64Utils;

import java.security.KeyPair;
import java.util.HashMap;
import java.util.Map;

import static com.example.utils.RSAUtils.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class MyTest {
    @Autowired
    private RedisService redisService;

    //    redis测试
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

    /*
        逻辑：前端获取publickey，用公钥对密码进行编码加密，后端获取密码，用相对应的私钥解密加密数据，与数据库比对密码是否一致
     */
    @Test
    public void testdecrypt() {
        try {
            // 生成密钥对
            KeyPair keyPair = getKeyPair();
            String privateKey = new String(Base64Utils.encode(keyPair.getPrivate().getEncoded()));
            String publicKey = new String(Base64Utils.encode(keyPair.getPublic().getEncoded()));
            System.out.println("私钥:" + privateKey);
            System.out.println("公钥:" + publicKey);
            // RSA加密
            String data = "123456";
            String encryptData = encrypt(data, getPublicKey(publicKey));
            System.out.println("加密后内容:" + encryptData);
            // RSA解密
            String decryptData = decrypt(encryptData, getPrivateKey(privateKey));
            System.out.println("解密后内容:" + decryptData);
            // RSA签名
            String sign = sign(data, getPrivateKey(privateKey));
            // RSA验签
            boolean result = verify(data, getPublicKey(publicKey), sign);
            System.out.print("验签结果:" + result);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.print("加解密异常");
        }
    }
}
