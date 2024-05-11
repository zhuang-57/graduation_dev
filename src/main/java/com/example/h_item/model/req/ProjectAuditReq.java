package com.example.h_item.model.req;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @Date 2024/4/27
 */
@Data
public class ProjectAuditReq {

    /**
     * 项目id
     */
    @NotNull(message = "项目id不能为空")
    private Long id;

    /**
     * 是否通过
     */
    @NotNull(message = "是否通过不能为空")
    private Boolean acceptFlag;
}
