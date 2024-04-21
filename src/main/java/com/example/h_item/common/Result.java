package com.example.h_item.common;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class Result<T> implements Serializable {

    private static final long serialVersionUID = -8963262177114836103L;

    protected T data;

    protected int code;

    protected String msg;

    public Result(){

    }

    @JsonCreator
    protected Result(@JsonProperty("code") int code, @JsonProperty("msg") String msg, @JsonProperty("data") T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static <T> Result<T> create(int status, String message, T result) {
        return new Result<>(status, message, result);
    }

    public static <T> Result<T> create(Status statusCode, T result) {
        return create(statusCode.getStatus(), statusCode.getReason(), result);
    }

    public static <T> Result<T> success(T result) {
        Status statusCode = Status.success();
        return create(statusCode, result);
    }

    public static <T> Result<T> success() {
        Status statusCode = Status.success();
        return create(statusCode, null);
    }

    public static <T> Result<T> error(String message) {
        Status statusCode = Status.error(message);
        return create(statusCode, null);
    }

    public static <T> Result<T> error(int status, String message) {
        Status statusCode = Status.error(status, message);
        return create(statusCode, null);
    }

    public static <T> Result<T> error(StatusCodeException e) {
        Status statusCode = e.getStatusCode();
        return create(statusCode, null);
    }

    public T checkAndGet() throws StatusCodeException {
        if (isSuccess()) {
            return getData();
        }
        throw Status.create(code, msg).asError();
    }

    public int getCode() {
        return code;
    }

    @JsonIgnore
    public boolean isSuccess() {
        return Status.create(code, msg).isSuccess();
    }

    public String getMsg() {
        return msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Result)) return false;

        Result<?> that = (Result<?>) o;

        if (code != that.code) return false;
        if (data != null ? !data.equals(that.data) : that.data != null) return false;
        return msg != null ? msg.equals(that.msg) : that.msg == null;
    }

    @Override
    public int hashCode() {
        int hashCode = data != null ? data.hashCode() : 0;
        hashCode = 31 * hashCode + code;
        hashCode = 31 * hashCode + (msg != null ? msg.hashCode() : 0);
        return hashCode;
    }

    @Override
    public String toString() {
        return "JsonResult{" +
                "data=" + data +
                ", code=" + code +
                ", msg='" + msg + '\'' +
                '}';
    }
}