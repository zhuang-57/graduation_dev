package com.example.h_item.model.req;

import com.example.h_item.common.PageRequest;
import lombok.Data;

/**
 * @Date 2024/4/27
 * @Created by wangshuai
 */
@Data
public class ProjectReq extends PageRequest {

    /**
     * 项目名称模糊查询
     */
    private String likeProjectName;

    /**
     * 只查自己的
     */
    private Boolean selfQuery;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 状态英文
     */
    private String status;
}
