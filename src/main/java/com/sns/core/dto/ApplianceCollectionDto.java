package com.sns.core.dto;

import java.util.List;

public class ApplianceCollectionDto {

    private List<CategoryDto> categories;

    public List<CategoryDto> getCategories() {
        return categories;
    }

    public void setCategories(List<CategoryDto> categories) {
        this.categories = categories;
    }
}
