package com.example.h_item.service;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.StrUtil;
import com.example.h_item.cache.LoginUtil;
import com.example.h_item.cache.UserLoginCache;
import com.example.h_item.common.Pager;
import com.example.h_item.common.StatusCodeException;
import com.example.h_item.enums.ProjectStatusEnum;
import com.example.h_item.mapper.ProjectMapper;
import com.example.h_item.model.po.ProjectPO;
import com.example.h_item.model.po.UserPO;
import com.example.h_item.model.req.*;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ProjectService {

    @Resource
    private ProjectMapper projectMapper;

    @Resource
    private UserService userService;

    @Resource
    private UserLoginCache userLoginCache;

    /**
     * 申请项目
     */
    public void apply(ProjectApplyReq req) {
        ProjectPO projectPO = new ProjectPO();
        BeanUtils.copyProperties(req, projectPO);
        Long userId = userLoginCache.getUserIdByToken(LoginUtil.get());
        UserPO userPO = userService.queryById(userId);
        Assert.notNull(userPO, "找不到用户信息");

        projectPO.setUserId(userId);
        projectPO.setUsername(userPO.getUsername());
        projectPO.setAcademyId(userPO.getAcademyId());
        projectPO.setStatus(ProjectStatusEnum.WAIT_AUDIT.name());
        projectMapper.insert(projectPO);
    }

    /**
     * 刪除
     **/
    public void delete(Long id) {
        projectMapper.delete(id);
    }

    /**
     * 更新
     **/
    public void update(ProjectUpdateReq req) {
        ProjectPO projectPO = new ProjectPO();
        projectPO.setId(req.getId());
        projectPO.setProName(req.getProName());
        projectPO.setLevel(req.getLevel());
        projectPO.setAcademyId(req.getAcademyId());
        projectPO.setType(req.getType());
        projectPO.setTeacherName(req.getTeacherName());
        projectPO.setTeacherPhone(req.getTeacherPhone());
        projectPO.setMember(req.getMember());
        projectPO.setLink(req.getLink());
        projectPO.setStartime(req.getStartime());
        projectPO.setEndtime(req.getEndtime());
        projectMapper.update(projectPO);
    }

    /**
     * 上传附件
     **/
    public void uploadAttachment(UploadAttachmentReq req) {
        ProjectPO projectPO = new ProjectPO();
        projectPO.setId(req.getId());
        projectPO.setMiddleCheckUrl(req.getMiddleCheckUrl());
        projectPO.setEndUrl(req.getEndUrl());
        projectMapper.update(projectPO);
    }

    /**
     * 更新
     **/
    public void update(ProjectPO project) {
        projectMapper.update(project);
    }

    /**
     * 查询 根据主键 id 查询
     **/
    public ProjectPO queryById(Long id) {
        ProjectPO projectPO = projectMapper.queryById(id);
        projectPO.setStatusName(ProjectStatusEnum.valueOf(projectPO.getStatus()).getDesc());
        return projectPO;
    }


    /**
     * 查询 分页查询
     **/
    public Pager<ProjectPO> pageList(ProjectReq req) {
        Long userId = userLoginCache.getUserIdByToken(LoginUtil.get());
        UserPO userPO = userService.queryById(userId);
        Assert.notNull(userPO, "找不到用户信息");
        if (userPO.getRoleId() != 3) {
            req.setUserId(userId);
        }
        List<ProjectPO> list = projectMapper.pageList(req);
        list.forEach(x -> x.setStatusName(ProjectStatusEnum.valueOf(x.getStatus()).getDesc()));
        return new Pager<>(new Pager.PageData(req.getPage(), projectMapper.pageListCount(req)), list);
    }

    public void audit(ProjectAuditReq req) {
        String token = LoginUtil.get();
        Long userId = userLoginCache.getUserIdByToken(token);
        UserPO userPO = userService.queryById(userId);
        Assert.isTrue(userPO.getRoleId() == 3,"只有管理员才能审核");
        ProjectPO projectPO = queryById(req.getId());
        Assert.notNull(projectPO, "找不到项目信息");
        if (!StrUtil.equalsAny(projectPO.getStatus(),ProjectStatusEnum.WAIT_AUDIT.name(),
                ProjectStatusEnum.WAIT_MIDDLE_CHECK.name(),ProjectStatusEnum.WAIT_END.name())) {
            StatusCodeException.throwException("只有待审核与待中期检测的项目需要审核");
        }
        if (!req.getAcceptFlag()) {
            projectPO.setStatus(ProjectStatusEnum.REFUSE.name());
        } else {
            if (projectPO.getStatus().equals(ProjectStatusEnum.WAIT_AUDIT.name())) {
                projectPO.setStatus(ProjectStatusEnum.WAIT_MIDDLE_CHECK.name());
            } else if (projectPO.getStatus().equals(ProjectStatusEnum.WAIT_MIDDLE_CHECK.name())) {
                projectPO.setStatus(ProjectStatusEnum.WAIT_END.name());
            }
            else if (projectPO.getStatus().equals(ProjectStatusEnum.WAIT_END.name())) {
                projectPO.setStatus(ProjectStatusEnum.END.name());
            }
        }
        update(projectPO);
    }
}
