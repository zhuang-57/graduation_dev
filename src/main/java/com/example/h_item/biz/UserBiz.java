package com.example.h_item.biz;

import cn.hutool.core.lang.Assert;
import com.example.h_item.cache.UserLoginCache;
import com.example.h_item.common.Pager;
import com.example.h_item.common.StatusCodeException;
import com.example.h_item.convert.UserConvert;
import com.example.h_item.model.dto.UserInfoDTO;
import com.example.h_item.model.dto.UserLoginDTO;
import com.example.h_item.model.po.RolePO;
import com.example.h_item.model.po.UserPO;
import com.example.h_item.model.req.*;
import com.example.h_item.service.RoleService;
import com.example.h_item.service.UserService;
import org.springframework.beans.BeanUtils;
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

    @Resource
    private UserConvert userConvert;

    @Resource
    private RoleService roleService;

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
     * 注册
     */
    public Boolean register(UserRegisterReq req) {
        UserPO findUser = userService.queryByUsername(req.getUsername());
        Assert.isNull(findUser, "用户名已经被使用");
        UserPO userPO = new UserPO();
        BeanUtils.copyProperties(req, userPO);
        userService.insert(userPO);
        return true;
    }

    /**
     * 分页查
     */
    public Pager<UserInfoDTO> query(UserReq req) {
        Pager<UserPO> pager = userService.query(req);
        return new Pager<>(pager.getPage(), userConvert.poToDTO(pager.getData()));
    }

    /**
     * 修改用户信息
     */
    public Boolean update(UserUpdateReq req, String token) {
        Long findUserId = userLoginCache.getUserIdByToken(token);
        UserPO findUser = userService.queryById(findUserId);
        // 修改用户名 校验用户名是否存在
        if (!Objects.equals(req.getUsername(), findUser.getUsername())) {
            UserPO userPO = userService.queryByUsername(req.getUsername());
            Assert.isNull(userPO, "用户名已经被使用");
        }
        UserPO userPO = new UserPO();
        BeanUtils.copyProperties(req, userPO);
        userPO.setId(findUserId);
        userService.updateById(userPO);
        return true;
    }

    /**
     * 修改密码
     */
    public Boolean updatePassword(UserUpdatePasswordReq req, String token) {
        Long findUserId = userLoginCache.getUserIdByToken(token);
        UserPO findUser = userService.queryById(findUserId);
        if (!Objects.equals(findUser.getPassword(), req.getPassword())) {
            StatusCodeException.throwException("旧密码不正确");
        }
        if (!Objects.equals(req.getNewPassword(), req.getAgainPassword())) {
            StatusCodeException.throwException("新密码两次填写不一致");
        }
        UserPO updatePO = new UserPO();
        updatePO.setId(findUserId);
        updatePO.setPassword(req.getNewPassword());
        userService.updateById(updatePO);
        // 修改密码后登出
        logout(token);
        return true;
    }

    /**
     * 获取用户信息
     */
    public UserInfoDTO getInfo(String token) {
        Long userId = userLoginCache.getUserIdByToken(token);
        UserPO userPO = userService.queryById(userId);
        return userConvert.poToDTO(userPO);
    }

    /**
     * 用户登出
     */
    public void logout(String token) {
        userLoginCache.delete(token);
    }

    /**
     * 更新角色
     */
    public void updateRole(UserUpdateRoleReq req) {
        RolePO rolePO = roleService.queryById(req.getRoleId());
        Assert.notNull(rolePO, "找不到需要更新的角色信息");
        UserPO userPO = userService.queryById(req.getUserId());
        Assert.notNull(userPO, "找不到对应的用户信息");
        userPO.setRoleId(req.getRoleId());
        userService.updateById(userPO);
    }
}
