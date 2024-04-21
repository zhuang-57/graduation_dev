package com.example.h_item.controller;


import com.example.h_item.biz.UserBiz;
import com.example.h_item.common.Result;
import com.example.h_item.model.dto.UserInfoDTO;
import com.example.h_item.model.dto.UserLoginDTO;
import com.example.h_item.model.req.UserLoginReq;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserBiz userBiz;

    @PostMapping("/login")
    public Result<UserLoginDTO> login(@RequestBody UserLoginReq req) {
        return Result.success(userBiz.login(req));
    }

    @GetMapping("/getInfo")
    public Result<UserInfoDTO> getInfo(@RequestHeader("token") String token) {
        return Result.success(userBiz.getInfo(token));
    }

    @PostMapping("/logout")
    public Result<Void> logout(@RequestHeader("token") String token) {
        userBiz.logout(token);
        return Result.success();
    }
}
