package com.khangse616.serverfashionrs.mappers.impl;

import com.khangse616.serverfashionrs.mappers.RowMapper;
import com.khangse616.serverfashionrs.models.Category;
import com.khangse616.serverfashionrs.models.dto.CategoryScreenDTO;
import com.khangse616.serverfashionrs.services.IImageDataService;

public class CategoryScreenDTOMapper implements RowMapper<CategoryScreenDTO, Category> {
    @Override
    public CategoryScreenDTO mapRow(Category category) {
        try {
            Category categoryParent = category.getParentCategory();
           return new CategoryScreenDTO(category.getId(), category.getLevel(), category.getName(), category.getIcon(), category.getPath(), category.getPathVarchar(), categoryParent==null?0:categoryParent.getId());
        } catch (Exception ex) {
            return null;
        }
    }

    @Override
    public CategoryScreenDTO mapRow(Category category, IImageDataService repository) {
        return null;
    }
}
