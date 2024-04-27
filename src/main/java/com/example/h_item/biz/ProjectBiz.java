package com.example.h_item.biz;

import cn.hutool.core.lang.Assert;
import com.example.h_item.common.StatusCodeException;
import com.example.h_item.model.dto.ProjectApplyDTO;
import com.example.h_item.model.dto.UserLoginDTO;
import com.example.h_item.model.po.UserPO;
import com.example.h_item.model.req.ProjectApplyReq;
import com.example.h_item.model.req.UserLoginReq;
import com.example.h_item.service.ProjectService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Objects;

@Component
public class ProjectBiz {

    @Resource
    private ProjectService projectService;

    /**
     * 项目申请
     */
    public ProjectApplyDTO insert(ProjectApplyReq req) {
        Assert.notBlank(req.getProId(), "项目id不能为空");
        Assert.notBlank(req.getProName(), "项目名称不能为空");
        Assert.notBlank(req.getUsername(), "用户名称不能为空");
        Assert.notBlank(req.getLevel(), "项目等级不能为空");
        Assert.notBlank(req.getAcademyId(), "学院id不能为空");
        Assert.notBlank(req.getType(), "类型不能为空");
        PrijectPO projectPO = projectService.queryByUsername(req.getUsername());
        Assert.notNull(userPO, "找不到用户信息");
        if (!Objects.equals(userPO.getPassword(), req.getPassword())) {
            StatusCodeException.throwException("密码错误");
        }

        // 信息校验成功
        String token = userLoginCache.login(userPO.getId());
        return new UserLoginDTO(token);
    }
}
