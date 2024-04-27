package com.example.h_item.model.po;

import lombok.Data;

import java.io.Serializable;
/**
 * @description 角色表
 * @author BEJSON
 * @date 2024-04-27
 */
@Data
public class RolesPO {


        private static final long serialVersionUID = 1L;

        @TableId(type = IdType.AUTO)
        /**
         * id
         */
        private Integer id;

        /**
         * 角色名称
         */
        private String rolesName;

        /**
         * 创建时间
         */
        private Date createTime;

        /**
         * 更新时间
         */
        private Date updateTime;

        /**
         * 角色描述
         */
        private String remark;

        public Roles() {}
}
