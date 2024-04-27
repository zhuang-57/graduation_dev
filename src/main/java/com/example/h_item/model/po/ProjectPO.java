package com.example.h_item.model.po;

import lombok.Data;

import java.util.Date;

/**
 * @author BEJSON
 * @description 项目表
 * @date 2024-04-22
 */
@Data
public class ProjectPO {

    /**
     * id
     */
    private Long id;

    /**
     * 项目名称
     */
    private String proName;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 用户名
     */
    private String username;

    /**
     * 状态
     */
    private String status;

    /**
     * 状态中文
     */
    private String statusName;

    /**
     * 等级
     */
    private String level;

    /**
     * 学院id
     */
    private Long academyId;

    /**
     * 类型
     */
    private String type;

    /**
     * 指导老师名称
     */
    private String teacherName;

    /**
     * 指导老师手机号
     */
    private String teacherPhone;

    /**
     * 成员名称
     */
    private String member;

    /**
     * 链接
     */
    private String link;

    /**
     * 备注
     */
    private String remark;

    /**
     * 项目预期启动时间
     */
    private Date startime;

    /**
     * 项目预期结束时间
     */
    private Date endtime;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

}
