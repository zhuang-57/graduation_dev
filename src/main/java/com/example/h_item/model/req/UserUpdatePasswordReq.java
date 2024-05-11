package com.example.h_item.model.req;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @Date 2024/4/27
 */
@Data
public class UserUpdatePasswordReq {

    @NotBlank(message = "旧密码不能为空")
    private String password;

    @NotBlank(message = "新密码不能为空")
    private String newPassword;

    @NotBlank(message = "请再次填写新密码")
    private String againPassword;
}
