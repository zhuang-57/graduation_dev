package com.example.h_item.service;


import com.example.h_item.mapper.UserMapper;
import com.example.h_item.model.po.UserPO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserService {

    @Resource
    private UserMapper userMapper;

    public Integer insert(UserPO userPO) {
        return userMapper.insert(userPO);
    }

    public UserPO queryById(Long id) {
        return userMapper.queryById(id);
    }

    public UserPO queryByUsername(String username) {
        return userMapper.queryByUsername(username);
    }
}
