package com.example.h_item.model.req;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @Date 2024/4/27
 */
@Data
public class UserUpdateReq {
    /**
     * 用户名
     */
    @NotBlank(message = "用户名不能为空")
    private String username;

    /**
     * 性别
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
    @NotNull(message = "学院不能为空")
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
