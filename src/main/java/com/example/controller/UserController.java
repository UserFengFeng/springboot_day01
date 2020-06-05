package com.example.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.config.tokenconfig.UserLoginToken;
import com.example.entitiy.User;
import com.example.service.RedisService;
import com.example.service.TokenService;
import com.example.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

import static com.example.utils.RSAUtils.*;

@RestController
@RequestMapping("/api")
public class UserController extends BaseController {
    @Autowired
    private UserService userService;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private RedisService redisService;

    private static Logger logger = LoggerFactory.getLogger(UserController.class);

    public static final String PAIRKEY = "PAIRKEY";

    @RequestMapping("/findAll")
    public List<User> findAll() {
        return userService.findAll();
    }

    //    登录
    @PostMapping("/login")
    public Object login(@RequestBody Map<String, Object> map) throws Exception {
        try {
            String username = (String) map.get("username");
            String password = (String) map.get("password");
            String publicKey = (String) map.get("publicKey");

            Map hash = redisService.getHash(PAIRKEY);
            String privateKey = (String) hash.get(publicKey);

            //  解密用户名
            String decryptUserName = decrypt(username, getPrivateKey(privateKey));
            //  解密密码
            String decryptPassword = decrypt(password, getPrivateKey(privateKey));
            //  签名
            String signUserName = sign(decryptUserName, getPrivateKey(privateKey));
            String signPassword = sign(decryptPassword, getPrivateKey(privateKey));
            //  验签
            boolean resultUname = verify(decryptUserName, getPublicKey(publicKey), signUserName);
            boolean resultPwd = verify(decryptPassword, getPublicKey(publicKey), signPassword);
            if (resultUname && resultPwd) {
                JSONObject jsonObject = new JSONObject();
                User userForBase = userService.findByUsername(decryptUserName);
                if (userForBase == null) {
                    return failedResult("登录失败，用户不存在!");
                } else {
                    if (!userForBase.getPassword().equals(decryptPassword)) {
                        return failedResult("登录失败,密码错误!");
                    } else {
                        String token = tokenService.getToken(userForBase);
                        jsonObject.put("token", token);
                        jsonObject.put("user", userForBase);
                        return successResult(jsonObject);
                    }
                }
            } else {
                return failedResult();
            }
        } catch (Exception e) {
            return failedResult(e.getMessage());
        }
    }

    @UserLoginToken
    @GetMapping("/getMessage")
    public String getMessage() {
        return "你已通过验证";
    }
}
