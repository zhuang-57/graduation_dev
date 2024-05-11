package com.example.h_item.model.req;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @Date 2024/4/27
 */
@Data
public class IdRequest {

    @NotNull(message = "id不能为空")
    private Long id;
}
