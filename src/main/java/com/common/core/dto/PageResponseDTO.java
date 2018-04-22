package com.common.core.dto;

import java.util.ArrayList;
import java.util.List;

import com.common.core.model.BaseObject;

public class PageResponseDTO<T> extends BaseObject {
    private static final long serialVersionUID = 3571606415879693166L;

    private List<T> data;
    private int totalRow = 0;

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
}
