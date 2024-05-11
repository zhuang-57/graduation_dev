package com.example.h_item.model.req;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @Date 2024/4/27
 */
@Data
public class RoleAddReq {

    /**
     * 角色名称
     */
    @NotBlank(message = "角色名称不能为空")
    private String rolesName;

    /**
     * 角色描述
     */
    @NotBlank(message = "描述不能为空")
    private String remark;
}
