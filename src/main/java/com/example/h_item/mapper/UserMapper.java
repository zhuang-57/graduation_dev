package com.example.h_item.mapper;

import com.example.h_item.model.po.UserPO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {

    Integer insert(UserPO userPO);

    UserPO queryById(Long id);

    UserPO queryByUsername(String username);

}
