package com.sns.core.dto;

import java.io.Serializable;
import java.util.List;

public class RenteeRequestResponseDto implements Serializable {

    private Integer pageCount;
    private List<RequestResponseDto> data;

    public Integer getPageCount() {
        return pageCount;
    }

    public void setPageCount(Integer pageCount) {
        this.pageCount = pageCount;
    }

    public List<RequestResponseDto> getData() {
        return data;
    }

    public void setData(List<RequestResponseDto> data) {
        this.data = data;
    }
}
