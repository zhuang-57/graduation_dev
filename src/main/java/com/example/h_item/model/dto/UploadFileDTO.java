package com.example.h_item.model.dto;

import lombok.Data;

/**
 * @Date 2022/2/23 22:32
 * @Created by wangshuai
 */
@Data
public class UploadFileDTO {
    private String oldName;
    private String newName;
    private String fileUrl;
}
