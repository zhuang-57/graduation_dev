package com.example.h_item.common;

import cn.hutool.core.util.StrUtil;
import org.springframework.util.Assert;

/**
 * @author fangming.yi
 * @version 1.0
 * @since 2020/10/20 20:06
 */
public class StatusCodeException extends RuntimeException {
    private final Status statusCode;

    protected StatusCodeException() {
        this.statusCode = Status.error("DEFAULT");
    }

    public StatusCodeException(Status statusCode) {
        super(assertNotNull(statusCode));
        this.statusCode = statusCode;
    }

    public StatusCodeException(String message, Status statusCode) {
        super(message + " " + assertNotNull(statusCode));
        this.statusCode = statusCode;
    }

    public StatusCodeException(String message, Throwable cause, Status statusCode) {
        super(message + " " + assertNotNull(statusCode), cause);
        this.statusCode = statusCode;
    }

    public StatusCodeException(Throwable cause, Status statusCode) {
        super(cause.getMessage() + " " + assertNotNull(statusCode), cause);
        this.statusCode = statusCode;
    }

    private static String assertNotNull(Status statusCode) {
        Assert.notNull(statusCode, "statusCode is required; it must not be null");
        return statusCode.toString();
    }

    public Status getStatusCode() {
        return statusCode;
    }

    static public void throwException(String errorMsgTemplate, Object... params){
        String strInfo = StrUtil.format(errorMsgTemplate, params);
        throw new StatusCodeException(Status.error(strInfo));
    }
}