package com.example.h_item.handler;

import com.example.h_item.common.Result;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Date 2023/6/16
 * @Created by wangshuai
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 全局异常捕获
     */
    @ExceptionHandler(value = Throwable.class)
    @ResponseBody
    public Result<String> handleException(Exception e) {
        return Result.error(e.getMessage());
    }
}
