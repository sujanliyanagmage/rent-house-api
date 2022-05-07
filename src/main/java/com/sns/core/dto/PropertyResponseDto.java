package com.sns.core.dto;

import com.sns.core.model.House;

import java.io.Serializable;
import java.util.List;

public class PropertyResponseDto implements Serializable {
    public Integer getPageCount() {
        return pageCount;
    }

    public void setPageCount(Integer pageCount) {
        this.pageCount = pageCount;
    }

    public List<House> getData() {
        return data;
    }

    public void setData(List<House> data) {
        this.data = data;
    }

    private Integer pageCount;
    private List<House> data;
}
