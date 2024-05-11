package com.example.h_item.model.req;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @Date 2024/4/27
 */
@Data
public class UserUpdateRoleReq {

    /**
     * 用户id
     */
    @NotNull(message = "用户id不能为空")
    private Long userId;

    /**
     * 角色id
     */
    @NotNull(message = "角色id不能为空")
    private Long roleId;
}
