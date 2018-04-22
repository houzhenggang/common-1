package com.common.core.dto;

import com.common.core.model.BaseObject;

public class PageRequestDTO<T> extends BaseObject {
    private static final long serialVersionUID = 4480303445151998440L;

    private T argument;// 查询参数
    private int pageNum;// 第几页（从第1开始计数）
    private int pageSize;// 每页查询数量

    public T getArgument() {
        return this.argument;
    }

    public void setArgument(T argument) {
        this.argument = argument;
    }

    public int getPageNum() {
        return this.pageNum;
    }

    public void setPageNum(int pageNum) {
        if (pageNum == 0) {
            throw new IllegalArgumentException("The pageNum start counting from one.");
        }
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return this.pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize <= 0 ? 10 : pageSize;
    }

    public int getOffset() {
        return (this.pageNum - 1) * this.pageSize;
    }
}
