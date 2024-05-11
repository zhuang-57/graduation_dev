package com.example.h_item.model.req;

import com.example.h_item.common.PageRequest;
import lombok.Data;

/**
 * @Date 2024/4/27
 */
@Data
public class UserReq extends PageRequest {

    /**
     * 用户名模糊查询
     */
    private String likeUsername;
}
