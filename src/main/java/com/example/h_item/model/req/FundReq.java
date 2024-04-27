package com.example.h_item.model.req;

import com.example.h_item.common.PageRequest;
import lombok.Data;

/**
 * @Date 2024/4/27
 * @Created by wangshuai
 */
@Data
public class FundReq extends PageRequest {

    /**
     * 项目id
     */
    private Long proId;

    /**
     * 项目名称
     */
    private String proName;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 状态
     */
    private String status;
}
