package com.example.h_item.model.req;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @Date 2024/4/27
 */
@Data
public class RoleUpdateReq {

    @NotNull(message = "角色id不能为空")
    private Long roleId;

    /**
     * 角色名
     */
    @NotBlank(message = "角色名不能为空")
    private String rolesName;

    /**
     * 备注
     */
    private String remark;
}
