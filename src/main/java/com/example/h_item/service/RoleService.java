package com.example.h_item.service;

import cn.hutool.core.lang.Assert;
import com.example.h_item.common.Pager;
import com.example.h_item.mapper.RoleMapper;
import com.example.h_item.model.po.RolePO;
import com.example.h_item.model.req.RoleAddReq;
import com.example.h_item.model.req.RoleReq;
import com.example.h_item.model.req.RoleUpdateReq;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;

/**
 * @Date 2024/4/27
 * @Created by wangshuai
 */
@Service
public class RoleService {

    @Resource
    private RoleMapper roleMapper;

    /**
     * 新增
     **/
    public boolean add(RoleAddReq req) {
        RolePO rolePO = roleMapper.queryByName(req.getRolesName());
        Assert.isNull(rolePO, "角色名已存在");
        rolePO = new RolePO();
        rolePO.setRolesName(req.getRolesName());
        rolePO.setRemark(req.getRemark());
        return roleMapper.insert(rolePO) == 1;
    }

    /**
     * 刪除
     **/
    public boolean delete(Long id) {
        roleMapper.delete(id);
        return true;
    }

    /**
     * 更新
     **/
    public Boolean update(RoleUpdateReq req) {
        RolePO findPO = roleMapper.queryByName(req.getRolesName());
        Assert.isTrue(findPO == null || Objects.equals(findPO.getId(), req.getRoleId()),
                "角色名已存在");

        RolePO rolePO = roleMapper.queryById(req.getRoleId());
        Assert.notNull(rolePO, "找不到对应角色信息");
        rolePO.setRolesName(req.getRolesName());
        rolePO.setRemark(req.getRemark());
        return roleMapper.update(rolePO) > 0;
    }

    /**
     * 查询 根据主键 id 查询
     **/
    public RolePO queryById(Long id) {
        return roleMapper.queryById(id);
    }

    /**
     * 查询 分页查询
     **/
    public Pager<RolePO> pageQuery(RoleReq req) {
        List<RolePO> list = roleMapper.pageQuery(req);
        return new Pager<>(new Pager.PageData(req.getPage(), roleMapper.count(req)), list);
    }
}
