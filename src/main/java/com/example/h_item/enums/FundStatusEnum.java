package com.example.h_item.enums;


public enum FundStatusEnum {

    WAIT_AUDIT("待审核"),

    CANCEL("取消"),

    ACCEPT("同意"),

    REFUSE("拒绝"),

    ;


    private String desc;

    FundStatusEnum(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

}
