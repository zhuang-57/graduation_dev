package com.example.h_item.enums;


public enum ProjectStatusEnum {

    WAIT_AUDIT("待审核"),

    REFUSE("驳回"),

    WAIT_MIDDLE_CHECK("待中期检查"),

    WAIT_END("待结题"),

    END("结束"),

    ;


    private String desc;

    ProjectStatusEnum(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

}
