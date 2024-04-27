package com.example.h_item.model.po;

import lombok.Data;

import java.util.Date;

/**
 * @author BEJSON
 * @description 角色表
 * @date 2024-04-27
 */
@Data
public class RolePO {

    /**
     * id
     */
    private Long id;

    /**
     * 角色名称
     */
    private String rolesName;

    /**
     * 角色描述
     */
    private String remark;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;
}
