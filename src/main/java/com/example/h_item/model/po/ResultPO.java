package com.example.h_item.model.po;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @description 成果表
 * @date 2024-04-27
 */
@Data
public class ResultPO {

    /**
     * id
     */
    private Long id;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 学院id
     */
    private Long academyId;

    /**
     * 项目名称
     */
    private String proName;

    /**
     * 备注
     */
    private String remark;

    /**
     * 状态
     */
    private String status;

    /**
     * 获奖类型
     */
    private String resultType;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;


}
