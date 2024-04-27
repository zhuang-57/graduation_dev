package com.example.h_item.mapper;

import com.example.h_item.model.po.UserPO;
import com.example.h_item.model.req.UserReq;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {

    Integer insert(UserPO userPO);

    Integer update(UserPO userPO);

    UserPO queryById(Long id);

    UserPO queryByUsername(String username);

    List<UserPO> query(UserReq req);

    int count(UserReq req);
}
