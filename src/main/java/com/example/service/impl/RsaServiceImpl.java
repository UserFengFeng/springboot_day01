package com.example.service.impl;

import com.example.service.RedisService;
import com.example.service.RsaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Base64Utils;

import java.security.KeyPair;
import java.util.HashMap;
import java.util.Map;

import static com.example.utils.RSAUtils.getKeyPair;

@Service
public class RsaServiceImpl implements RsaService {
    @Autowired
    private RedisService redisService;

    public static final String PAIRKEY = "PAIRKEY";

    @Override
    public String getPublicKey() {
        try {

            Map<String, String> map = new HashMap<>();

            KeyPair keyPair = getKeyPair();
            String privateKey = new String(org.springframework.util.Base64Utils.encode(keyPair.getPrivate().getEncoded()));
            String publicKey = new String(Base64Utils.encode(keyPair.getPublic().getEncoded()));

            map.put(publicKey, privateKey);
            redisService.setHash(PAIRKEY, map);
            return publicKey;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
