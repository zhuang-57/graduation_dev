package com.example.h_item.model.po;

import lombok.Data;

import java.io.Serializable;
/**
 * @description 学院表
 * @author BEJSON
 * @date 2024-04-27
 */
@Data
public class AcademyPO {


        private static final long serialVersionUID = 1L;

        @TableId(type = IdType.AUTO)
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

        public Academy() {}
}
