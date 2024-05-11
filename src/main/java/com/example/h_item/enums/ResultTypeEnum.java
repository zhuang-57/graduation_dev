package com.example.h_item.enums;


public enum ResultTypeEnum {

    TOPIC("课题"),

    BOOK("论著"),

    PATENT("专利"),

    PAPER("论文"),

    WRITINGS("著作"),

    ;


    private String desc;

    ResultTypeEnum(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

}
