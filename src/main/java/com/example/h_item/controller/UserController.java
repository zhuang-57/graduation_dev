package com.example.h_item.controller;


import cn.hutool.core.lang.Assert;
import com.example.h_item.biz.UserBiz;
import com.example.h_item.common.Pager;
import com.example.h_item.common.Result;
import com.example.h_item.model.dto.UserInfoDTO;
import com.example.h_item.model.dto.UserLoginDTO;
import com.example.h_item.model.req.*;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserBiz userBiz;

    @PostMapping("/login")
    public Result<UserLoginDTO> login(@RequestBody UserLoginReq req) {
        return Result.success(userBiz.login(req));
    }

    @PostMapping("/register")
    public Result<Boolean> register(@RequestBody @Valid UserRegisterReq req) {
        return Result.success(userBiz.register(req));
    }

    @PostMapping("/query")
    public Result<Pager<UserInfoDTO>> query(@RequestBody @Valid UserReq req) {
        return Result.success(userBiz.query(req));
    }

    @PostMapping("/update")
    public Result<Boolean> update(@RequestBody @Valid UserUpdateReq req) {
        return Result.success(userBiz.update(req));
    }

    @PostMapping("/delete")
    public Result<Boolean> delete(@RequestBody @Valid IdRequest req) {
        return Result.success(userBiz.delete(req));
    }

    @PostMapping("/updatePassword")
    public Result<Boolean> updatePassword(@RequestBody @Valid UserUpdatePasswordReq req) {
        return Result.success(userBiz.updatePassword(req));
    }

    @GetMapping("/getInfo")
    public Result<UserInfoDTO> getInfo() {
        return Result.success(userBiz.getInfo());
    }

    @PostMapping("/logout")
    public Result<Void> logout() {
        userBiz.logout();
        return Result.success();
    }

    @PostMapping("/updateRole")
    public Result<Void> updateRole(@RequestBody UserUpdateRoleReq req) {
        userBiz.updateRole(req);
        return Result.success();
    }
}
