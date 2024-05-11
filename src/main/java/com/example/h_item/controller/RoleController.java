package com.example.h_item.controller;

import com.example.h_item.common.Pager;
import com.example.h_item.common.Result;
import com.example.h_item.model.po.RolePO;
import com.example.h_item.model.req.*;
import com.example.h_item.service.RoleService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;

@RestController
@RequestMapping("/role")
public class RoleController {

    @Resource
    private RoleService roleService;

    @PostMapping("/add")
    public Result<Boolean> add(@RequestBody @Valid RoleAddReq req) {
        return Result.success(roleService.add(req));
    }

    @PostMapping("/update")
    public Result<Boolean> update(@RequestBody @Valid RoleUpdateReq req) {
        return Result.success(roleService.update(req));
    }

    @PostMapping("/delete")
    public Result<Boolean> delete(@RequestBody @Valid IdRequest req) {
        return Result.success(roleService.delete(req.getId()));
    }

    @PostMapping("/pageQuery")
    public Result<Pager<RolePO>> pageQuery(@RequestBody RoleReq req) {
        return Result.success(roleService.pageQuery(req));
    }
}
