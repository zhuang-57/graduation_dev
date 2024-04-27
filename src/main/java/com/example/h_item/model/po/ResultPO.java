package com.example.h_item.model.po;

import lombok.Data;

import java.io.Serializable;
/**
 * @description 成果表
 * @author BEJSON
 * @date 2024-04-27
 */
@Data
public class ResultPO {

        private static final long serialVersionUID = 1L;

        @TableId(type = IdType.AUTO)
        /**
         * id
         */
        private Integer id;

        /**
         * 用户id
         */
        private Integer userId;

        /**
         * 学院id
         */
        private Integer academyId;

        /**
         * 项目名称
         */
        private String proName;

        /**
         * 获奖名称
         */
        private String rewordName;

        /**
         * 颁发机构
         */
        private String issuingAuth;

        /**
         * 获奖级别
         */
        private String rewardType;

        /**
         * 获奖时间
         */
        private Date rewardTime;

        /**
         * 备注
         */
        private String remark;

        public Result() {}

}
