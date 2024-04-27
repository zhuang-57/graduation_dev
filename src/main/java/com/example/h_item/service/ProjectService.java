package com.example.h_item.service;

import com.example.h_item.mapper.ProjectMapper;
import com.example.h_item.model.po.ProjectPO;
import com.example.h_item.model.po.UserPO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class ProjectService {

    @Resource
    private ProjectMapper projectMapper;

    public Integer insert(ProjectPO projectPO) {
        return projectMapper.insert(projectPO);
    }
}
