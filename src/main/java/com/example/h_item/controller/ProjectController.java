package com.example.h_item.controller;


import cn.hutool.core.lang.Assert;
import com.example.h_item.biz.ProjectBiz;
import com.example.h_item.biz.UserBiz;
import com.example.h_item.common.Result;
import com.example.h_item.model.dto.ProjectApplyDTO;
import com.example.h_item.model.dto.UserInfoDTO;
import com.example.h_item.model.dto.UserLoginDTO;
import com.example.h_item.model.req.ProjectApplyReq;
import com.example.h_item.model.req.UserLoginReq;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/project")
public class ProjectController {

    @Resource
    private ProjectBiz projectBiz;

    @PostMapping("/add")
    public Result<ProjectApplyDTO> insert(@RequestBody ProjectApplyReq req) {
        return Result.success(ProjectBiz.insert(req));
    }

    @GetMapping("/getInfo")
    public Result<UserInfoDTO> getInfo(@RequestHeader("token") String token) {
        Assert.notBlank(token,"token不能为空");
        return Result.success(userBiz.getInfo(token));
    }

    @PostMapping("/logout")
    public Result<Void> logout(@RequestHeader("token") String token) {
        Assert.notBlank(token,"token不能为空");
        userBiz.logout(token);
        return Result.success();
    }
}
