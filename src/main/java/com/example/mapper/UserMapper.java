package com.example.mapper;

import com.example.entitiy.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
    List<User> getUsers();

    User findUserById(Integer id);
    User findByUsername(String username);
}
