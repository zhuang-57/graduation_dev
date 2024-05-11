package com.example.h_item.model.po;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @description 学院表
 * @date 2024-04-27
 */
@Data
public class AcademyPO {

    /**
     * id
     */
    private Integer id;

    /**
     * 学院名称
     */
    private String acadName;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

}
