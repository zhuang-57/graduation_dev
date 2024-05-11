package com.example.h_item.model.req;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class ResultAuditReq {

    /**
     * 成果id
     */
    @NotNull(message = "成果id不能为空")
    private Long resultId;

    /**
     * 是否通过
     */
    @NotNull(message = "是否通过不能为空")
    private Boolean acceptFlag;
}
