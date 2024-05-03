package com.example.h_item.model.req;

import lombok.Data;

import java.util.Date;

@Data
public class ProjectUpdateReq {

    private Long id;

    /**
     * 项目名称
     */
    private String proName;

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

}
