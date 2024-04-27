package com.example.h_item.mapper;

import com.example.h_item.model.po.RolePO;
import com.example.h_item.model.req.RoleReq;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RoleMapper {

    /**
     * 新增
     **/
    int insert(RolePO roles);

    RolePO queryByName(String name);

    /**
     * 刪除
     **/
    int delete(Long id);

    /**
     * 更新
     **/
    int update(RolePO roles);

    /**
     * 查询 根据主键 id 查询
     **/
    RolePO queryById(Long id);

    /**
     * 查询 分页查询
     **/
    List<RolePO> pageQuery(RoleReq req);

    /**
     * 查询 分页查询 count
     **/
    int count(RoleReq req);

}