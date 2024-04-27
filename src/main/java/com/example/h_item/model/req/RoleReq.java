package com.example.h_item.model.req;

import com.example.h_item.common.PageRequest;
import lombok.Data;

/**
 * @Date 2024/4/27
 * @Created by wangshuai
 */
@Data
public class RoleReq extends PageRequest {

    /**
     * 角色名称 模糊查询
     */
    private String likeRoleName;
}
