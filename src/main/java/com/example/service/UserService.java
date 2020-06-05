package com.example.service;

import com.example.entitiy.User;

import java.util.List;

public interface UserService {

    List<User> findAll();

    User findUserById(Integer id);

    User findByUsername(String username);
}
