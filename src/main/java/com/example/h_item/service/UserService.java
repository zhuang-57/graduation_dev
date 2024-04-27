package com.example.h_item.service;


import com.example.h_item.common.Pager;
import com.example.h_item.mapper.UserMapper;
import com.example.h_item.model.po.UserPO;
import com.example.h_item.model.req.UserReq;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

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

    public Pager<UserPO> query(UserReq req) {
        List<UserPO> userPOList = userMapper.query(req);
        return new Pager<>(new Pager.PageData(req.getPage(), userMapper.count(req)), userPOList);
    }

    public void updateById(UserPO userPO) {
        userMapper.update(userPO);
    }
}
