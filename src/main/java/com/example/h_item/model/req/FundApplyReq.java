package com.example.h_item.model.req;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @Date 2024/4/27
 */
@Data
public class FundApplyReq {

    /**
     * 项目id
     */
    @NotNull(message = "项目id不能为空")
    private Long proId;

    /**
     * 金额
     */
    @NotNull(message = "金额不能为空")
    private Long fund;

    /**
     * 账号
     */
    @NotBlank(message = "打款账号不能为空")
    private String account;

    /**
     * 备注
     */
    private String remark;
}
