package com.sns.core.dto;

import java.util.List;

public class CategoryDto {
    private String title;
    private List<?> features;

    public List<?> getFeatures() {
        return features;
    }

    public void setFeatures(List<?> features) {
        this.features = features;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
