package com.example.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.entitiy.User;

public interface TokenService {
    public String getToken(User user);
}
