package com.example.h_item.common;


public enum BusinessErrorCodeEnum {
    TOKEN_OVERDUE(1000, "token过期请重新登陆");
    private final Integer code;

    private final String desc;

    public Integer getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

    BusinessErrorCodeEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
