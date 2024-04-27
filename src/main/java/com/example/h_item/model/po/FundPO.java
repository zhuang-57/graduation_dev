package com.example.h_item.model.po;

import lombok.Data;

import java.io.Serializable;
/**
 * @description 经费表
 * @author BEJSON
 * @date 2024-04-27
 */
@Data
public class FundPO {


        private static final long serialVersionUID = 1L;

        @TableId(type = IdType.AUTO)
        /**
         * id
         */
        private Integer id;

        /**
         * 项目id
         */
        private Long proId;

        /**
         * 项目名称
         */
        private String proName;

        /**
         * 科研金额
         */
        private Long fund;

        /**
         * 科研账号
         */
        private String account;

        /**
         * 备注
         */
        private String book;

        public Fund() {}
}
