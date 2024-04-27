package com.example.h_item.model.req;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @Date 2024/4/27
 * @Created by wangshuai
 */
@Data
public class FundAuditReq {

    /**
     * 经费id
     */
    @NotNull(message = "经费id不能为空")
    private Long fundId;

    /**
     * 是否通过
     */
    @NotNull(message = "是否通过不能为空")
    private Boolean acceptFlag;
}
