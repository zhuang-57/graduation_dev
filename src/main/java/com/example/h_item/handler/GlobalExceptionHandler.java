package com.example.h_item.handler;

import com.example.h_item.common.Result;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;


@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    @ResponseBody
    public Result<String> handleBusinessException(MethodArgumentNotValidException e) {
        return Result.error(e.getBindingResult().getAllErrors().get(0).getDefaultMessage());
    }

    /**
     * 全局异常捕获
     */
    @ExceptionHandler(value = Throwable.class)
    @ResponseBody
    public Result<String> handleException(Exception e) {
        return Result.error(e.getMessage());
    }
}
