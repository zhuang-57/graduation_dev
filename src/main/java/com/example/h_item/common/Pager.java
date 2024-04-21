package com.example.h_item.common;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.Function;
import com.google.common.collect.Lists;

import java.io.Serializable;
import java.util.List;

/**
 * @author fangming.yi
 * @version 1.0
 * @since 2020/10/20 20:39
 */
public class Pager<T> implements Serializable {

    /** 分页数据 */
    private PageData page;

    /** list of data */
    private List<T> data;

    public Pager() {
    }

    @JsonCreator
    public Pager(@JsonProperty("page") PageData page, @JsonProperty("data") List<T> data) {
        this.page = page;
        this.data = Lists.newArrayList();
        this.data.addAll(data);
    }

    public static <T> Builder<T> builder(List<T> data) {
        return new Builder<T>().data(data);
    }

    public PageData getPage() {
        return page;
    }

    public List<T> getData() {
        return data == null ? Lists.newArrayList() : data;
    }

    public void setPage(PageData page) {
        this.page = page;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public boolean hasData() {
        return data != null && !data.isEmpty();
    }

    public int getDataSize() {
        return data == null ? 0 : data.size();
    }

    public <E> Pager<E> transform(Function<T, E> function) {
        return new Pager<>(page, Lists.transform(data, function));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Pager)) return false;

        Pager<?> pager = (Pager<?>) o;

        if (page != null ? !page.equals(pager.page) : pager.page != null) return false;
        return data != null ? data.equals(pager.data) : pager.data == null;
    }

    @Override
    public int hashCode() {
        int result = page != null ? page.hashCode() : 0;
        result = 31 * result + (data != null ? data.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Pager{" +
                "page=" + page +
                ", data=" + data +
                '}';
    }

    public static class Builder<T> {

        private List<T> data;

        private PageRequest.Page page;

        private int totalSize = 0;

        private Builder() {
        }

        public Builder<T> current(PageRequest.Page page) {
            this.page = page;
            return this;
        }

        public Builder<T> total(int totalSize) {
            this.totalSize = totalSize;
            return this;
        }

        public Builder<T> data(List<T> data) {
            this.data = data;
            return this;
        }

        public Pager<T> create() {
            return new Pager<>(new PageData(page, this.totalSize), data);
        }
    }

    public static class PageData implements Serializable {

        private static final long serialVersionUID = 3599580483667456581L;

        private int curPage;

        private int pageSize;

        private int totalSize;

        private Sort sort;

        public PageData() {
        }

        public PageData(int curPage, int pageSize, int totalSize) {
            this.curPage = curPage;
            this.pageSize = pageSize;
            this.totalSize = totalSize;
        }

        @JsonCreator
        public PageData(@JsonProperty("curPage") int curPage, @JsonProperty("pageSize") int pageSize, @JsonProperty("sort") Sort sort, @JsonProperty("totalSize") int totalSize) {
            this.curPage = curPage;
            this.pageSize = pageSize;
            this.sort = sort;
            this.totalSize = totalSize;
        }

        public PageData(PageRequest.Page page, int totalSize) {
            if (page != null) {
                this.curPage = page.getPageNo();
                this.pageSize = page.getPageSize();
                this.sort = page.getSort();
            }
            this.totalSize = totalSize;
        }

        public int getCurPage() {
            return curPage;
        }

        public boolean hasPreviousPage() {
            return getCurPage() > 1;
        }

        public boolean isFirstPage() {
            return !hasPreviousPage();
        }

        public int getPageSize() {
            return pageSize;
        }

        public int getTotalSize() {
            return totalSize;
        }

        public boolean hasNextPage() {
            return getCurPage() < getTotalPage();
        }

        public boolean isLastPage() {
            return !hasNextPage();
        }

        public int getTotalPage() {
            return getPageSize() == 0 ? 1 : (int) Math.ceil((double) totalSize / (double) getPageSize());
        }

        public Sort getSort() {
            return sort;
        }

        public void setCurPage(int curPage) {
            this.curPage = curPage;
        }

        public void setPageSize(int pageSize) {
            this.pageSize = pageSize;
        }

        public void setTotalSize(int totalSize) {
            this.totalSize = totalSize;
        }

        public void setSort(Sort sort) {
            this.sort = sort;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof PageData)) return false;

            PageData pageData = (PageData) o;

            if (curPage != pageData.curPage) return false;
            if (pageSize != pageData.pageSize) return false;
            if (totalSize != pageData.totalSize) return false;
            return sort != null ? sort.equals(pageData.sort) : pageData.sort == null;
        }

        @Override
        public int hashCode() {
            int result = curPage;
            result = 31 * result + pageSize;
            result = 31 * result + totalSize;
            result = 31 * result + (sort != null ? sort.hashCode() : 0);
            return result;
        }

        @Override
        public String toString() {
            return "PageData{" +
                    "curPage=" + curPage +
                    ", pageSize=" + pageSize +
                    ", totalSize=" + totalSize +
                    ", sort=" + sort +
                    '}';
        }
    }
}