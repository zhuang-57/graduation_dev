package com.example.h_item.biz;

import cn.hutool.core.lang.Assert;
import com.example.h_item.cache.UserLoginCache;
import com.example.h_item.common.StatusCodeException;
import com.example.h_item.convert.UserConvert;
import com.example.h_item.model.dto.UserInfoDTO;
import com.example.h_item.model.dto.UserLoginDTO;
import com.example.h_item.model.po.UserPO;
import com.example.h_item.model.req.UserLoginReq;
import com.example.h_item.service.UserService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Objects;

/**
 * @Date 2024/4/21
 * @Created by wangshuai
 */
@Component
public class UserBiz {

    @Resource
    private UserService userService;

    @Resource
    private UserLoginCache userLoginCache;

    /**
     * 用户登录
     */
    public UserLoginDTO login(UserLoginReq req) {
        Assert.notBlank(req.getUsername(), "用户名不能为空");
        Assert.notBlank(req.getPassword(), "用户密码不能为空");
        UserPO userPO = userService.queryByUsername(req.getUsername());
        Assert.notNull(userPO, "找不到用户信息");
        if (!Objects.equals(userPO.getPassword(), req.getPassword())) {
            StatusCodeException.throwException("密码错误");
        }

        // 信息校验成功
        String token = userLoginCache.login(userPO.getId());
        return new UserLoginDTO(token);
    }

    /**
     * 获取用户信息
     */
    public UserInfoDTO getInfo(String token) {
        Long userId = userLoginCache.getUserIdByToken(token);
        UserPO userPO = userService.queryById(userId);
        return UserConvert.poToDTO(userPO);
    }

    /**
     * 用户登出
     */
    public void logout(String token) {
        userLoginCache.delete(token);
    }
}
