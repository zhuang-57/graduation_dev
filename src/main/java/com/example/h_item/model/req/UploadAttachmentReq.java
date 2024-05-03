package com.example.h_item.model.req;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class UploadAttachmentReq {

    /**
     * 项目id
     */
    @NotNull(message = "id不能为空")
    private Long id;
    /**
     * 中期检查文件地址
     */
    private String middleCheckUrl;
    /**
     * 结题文件地址
     */
    private String endUrl;
}
