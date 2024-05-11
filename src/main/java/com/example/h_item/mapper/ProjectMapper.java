package com.example.h_item.mapper;

import com.example.h_item.model.po.ProjectPO;
import com.example.h_item.model.req.ProjectReq;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @description 项目表
 * @date 2024-04-27
 */
@Mapper
public interface ProjectMapper {

    /**
    * 新增
    **/
    int insert(ProjectPO project);

    /**
    * 刪除
    **/
    int delete(Long id);

    /**
    * 更新
    **/
    int update(ProjectPO project);

    /**
    * 查询 根据主键 id 查询
    **/
    ProjectPO queryById(Long id);


    /**
    * 查询 分页查询
    **/
    List<ProjectPO> pageList(ProjectReq req);

    /**
    * 查询 分页查询 count
    **/
    int pageListCount(ProjectReq req);
}