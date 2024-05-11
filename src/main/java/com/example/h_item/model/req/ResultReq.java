package com.example.h_item.model.req;

import com.example.h_item.common.PageRequest;
import lombok.Data;

import java.util.Date;

@Data
public class ResultReq extends PageRequest {

    /**
     * 项目名称
     */
    private String likeProName;
    /**
     * 成果类型
     */
    private String resultType;

    private String status;

    /**
     * 学院id
     */
    private Long academyId;

    private Date startTime;

    private Date endTime;
}
