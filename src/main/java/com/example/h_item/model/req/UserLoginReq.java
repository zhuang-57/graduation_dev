package com.example.h_item.model.req;

import lombok.Data;

@Data
public class UserLoginReq {

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

}
