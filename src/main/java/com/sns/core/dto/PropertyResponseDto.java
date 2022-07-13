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

    public List<HouseResponseDto> getData() {
        return data;
    }

    public void setData(List<HouseResponseDto> data) {
        this.data = data;
    }

    private Integer pageCount;
    private List<HouseResponseDto> data;
}
