package com.example.h_item.model.req;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class ResultApplyReq {

    /**
     * 项目id
     */
    @NotNull(message = "项目id不能为空")
    private Long projectId;

    @NotBlank(message = "成果类型不能为空")
    private String resultType;

    private String remark;
}
