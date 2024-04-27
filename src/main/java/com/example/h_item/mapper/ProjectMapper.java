package com.example.h_item.mapper;

import com.example.h_item.model.po.ProjectPO;
import com.example.h_item.model.po.UserPO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ProjectMapper {

    Integer insert(ProjectPO projectPO);

    ProjectPO queryById(Long id);

    ProjectPO queryByUsername(String username);

}
