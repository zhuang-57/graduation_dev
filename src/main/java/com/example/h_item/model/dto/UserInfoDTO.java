package com.example.h_item.model.dto;

import lombok.Data;

import java.util.Date;

/**
 * @Date 2024/4/21
 */
@Data
public class UserInfoDTO {

    /**
     * id
     */
    private Long id;

    /**
     * username
     */
    private String username;

    /**
     * password
     */
    private String password;

    /**
     * 角色id
     */
    private Long roleId;

    /**
     * 角色名称
     */
    private String roleName;

    /**
     * sex
     */
    private String sex;

    /**
     * birthday
     */
    private Date birthday;

    /**
     * img
     */
    private String img;

    /**
     * 学院id
     */
    private Long academyId;

    /**
     * 学历
     */
    private String educational;

    /**
     * 专业
     */
    private String major;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 个人简介
     */
    private String remark;
}
