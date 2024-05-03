package com.example.h_item.controller;


import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.StrUtil;
import com.example.h_item.cache.LoginUtil;
import com.example.h_item.common.Pager;
import com.example.h_item.common.Result;
import com.example.h_item.common.StatusCodeException;
import com.example.h_item.enums.ProjectStatusEnum;
import com.example.h_item.model.po.ProjectPO;
import com.example.h_item.model.req.*;
import com.example.h_item.service.ProjectService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

@RestController
@RequestMapping("/project")
public class ProjectController {

    @Resource
    private ProjectService projectService;

    @PostMapping("/apply")
    public Result<Void> apply(@RequestBody ProjectApplyReq req) {
        projectService.apply(req);
        return Result.success();
    }

    @PostMapping("/delete")
    public Result<Void> delete(@RequestBody IdRequest req) {
        projectService.delete(req.getId());
        return Result.success();
    }

    @PostMapping("/update")
    public Result<Void> update(@RequestBody ProjectUpdateReq req) {
        Assert.notNull(req.getId(), "id不能为空");
        projectService.update(req);
        return Result.success();
    }

    @PostMapping("/uploadAttachment")
    public Result<Void> uploadAttachment(@RequestBody @Valid UploadAttachmentReq req) {
        projectService.uploadAttachment(req);
        return Result.success();
    }

    @PostMapping("/queryById")
    public Result<ProjectPO> queryById(@RequestBody @Valid IdRequest req) {
        return Result.success(projectService.queryById(req.getId()));
    }

    @PostMapping("/pageQuery")
    public Result<Pager<ProjectPO>> pageQuery(@RequestBody @Valid ProjectReq req) {
        return Result.success(projectService.pageList(req));
    }

    @PostMapping("/audit")
    public Result<Void> audit(@RequestBody @Valid ProjectAuditReq req) {
        projectService.audit(req);
        return Result.success();
    }
}
