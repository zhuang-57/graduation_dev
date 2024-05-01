package com.example.h_item.service;

import cn.hutool.core.lang.Assert;
import com.example.h_item.cache.UserLoginCache;
import com.example.h_item.common.Pager;
import com.example.h_item.enums.ProjectStatusEnum;
import com.example.h_item.mapper.ProjectMapper;
import com.example.h_item.model.po.ProjectPO;
import com.example.h_item.model.po.UserPO;
import com.example.h_item.model.req.ProjectApplyReq;
import com.example.h_item.model.req.ProjectReq;
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
    public void apply(ProjectApplyReq req, String token) {
        ProjectPO projectPO = new ProjectPO();
        BeanUtils.copyProperties(req, projectPO);
        Long userId = userLoginCache.getUserIdByToken(token);
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
        List<ProjectPO> list = projectMapper.pageList(req);
        list.forEach(x -> x.setStatusName(ProjectStatusEnum.valueOf(x.getStatus()).getDesc()));
        return new Pager<>(new Pager.PageData(req.getPage(), projectMapper.pageListCount(req)), list);
    }

}
