package com.common.core.dto;

import java.util.ArrayList;
import java.util.List;

import com.common.core.model.BaseObject;

public class PageDTO<T, S> extends BaseObject {
    private static final long serialVersionUID = -3117352321619838300L;
    private S argument;
    private List<T> data;
    private int totalRow;
    private int pageNum;
    private int pageSize;

    public S getArgument() {
        return (S) this.argument;
    }

    public void setArgument(S argument) {
        this.argument = argument;
    }

    public List<T> getData() {
        if (this.data == null) {
            this.data = new ArrayList<T>(0);
        }
        return this.data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public int getTotalRow() {
        return this.totalRow;
    }

    public void setTotalRow(int totalRow) {
        this.totalRow = totalRow;
    }

    public int getPageNum() {
        return this.pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return this.pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getOffset() {
        return (this.pageNum < 0 ? 0 : this.pageNum) * this.pageSize;
    }
}
