package com.example.h_item.controller;


import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.StrUtil;
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
    public Result<Void> apply(@RequestBody ProjectApplyReq req, @RequestHeader("token") String token) {
        Assert.notBlank(token, "token不能为空");
        projectService.apply(req, token);
        return Result.success();
    }

    @PostMapping("/delete")
    public Result<Void> delete(@RequestBody IdRequest req) {
        projectService.delete(req.getId());
        return Result.success();
    }

    @PostMapping("/update")
    public Result<Void> update(@RequestBody ProjectPO projectPO) {
        Assert.notNull(projectPO.getId(), "id不能为空");
        projectService.update(projectPO);
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
        ProjectPO projectPO = projectService.queryById(req.getId());
        Assert.notNull(projectPO, "找不到项目信息");
        if (StrUtil.equalsAny(ProjectStatusEnum.WAIT_AUDIT.name(),
                ProjectStatusEnum.WAIT_MIDDLE_CHECK.name())) {
            StatusCodeException.throwException("只有待审核与待中期检测的项目需要审核");
        }
        if (!req.getAcceptFlag()) {
            projectPO.setStatus(ProjectStatusEnum.REFUSE.name());
        } else {
            if (projectPO.getStatus().equals(ProjectStatusEnum.WAIT_AUDIT.name())) {
                projectPO.setStatus(ProjectStatusEnum.WAIT_MIDDLE_CHECK.name());
            } else if (projectPO.getStatus().equals(ProjectStatusEnum.WAIT_MIDDLE_CHECK.name())) {
                projectPO.setStatus(ProjectStatusEnum.END.name());
            }
        }
        projectService.update(projectPO);
        return Result.success();
    }
}
