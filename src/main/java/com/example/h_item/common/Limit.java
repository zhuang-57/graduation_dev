package com.example.h_item.common;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * @author fangming.yi
 * @version 1.0
 * @since 2020/10/20 20:44
 */
public class Limit implements Serializable {

    protected int page;

    protected int offset;

    protected int size;

    public Limit() {

    }

    @JsonCreator
    protected Limit(@JsonProperty("page") int page, @JsonProperty("offset") int offset, @JsonProperty("size") int size) {
        this.page = page;
        this.offset = offset;
        this.size = size;
    }

    public static Limit createByOffset(int offset, int size) {
        return new Limit((offset / size) + 1, offset, size);
    }

    public static Limit createByPage(int page, int size) {
        return new Limit(page, (page - 1) * size, size);
    }

    public int getPage() {
        return page;
    }

    public int getOffset() {
        return offset;
    }

    public int getSize() {
        return size;
    }


    public void setPage(int page) {
        this.page = page;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public void setSize(int size) {
        this.size = size;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Limit)) return false;

        Limit limit = (Limit) o;

        if (page != limit.page) return false;
        return size == limit.size;
    }

    @Override
    public int hashCode() {
        int result = page;
        result = 31 * result + size;
        return result;
    }

    @Override
    public String toString() {
        return "Limit{" +
                "page=" + page +
                ", offset=" + offset +
                ", size=" + size +
                '}';
    }
}