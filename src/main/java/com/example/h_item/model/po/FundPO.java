package com.example.h_item.model.po;

import lombok.Data;

import java.util.Date;

/**
 * @description 经费表
 * @date 2024-04-27
 */
@Data
public class FundPO {

    /**
     * id
     */
    private Integer id;

    /**
     * 项目id
     */
    private Long proId;

    /**
     * 项目名称
     */
    private String proName;

    /**
     * 科研金额
     */
    private Long fund;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 科研账号
     */
    private String account;

    /**
     * 备注
     */
    private String remark;

    /**
     * 状态
     */
    private String status;

    private Date createTime;

    private Date updateTime;

}
