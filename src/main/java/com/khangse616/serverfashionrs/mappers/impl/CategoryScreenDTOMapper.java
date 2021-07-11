package com.khangse616.serverfashionrs.mappers.impl;

import com.khangse616.serverfashionrs.Utils.StringUtil;
import com.khangse616.serverfashionrs.mappers.RowMapper;
import com.khangse616.serverfashionrs.models.Category;
import com.khangse616.serverfashionrs.models.dto.CategoryScreenDTO;
import com.khangse616.serverfashionrs.models.dto.PathCategoryDTO;
import com.khangse616.serverfashionrs.services.IImageDataService;
import org.apache.commons.lang3.ArrayUtils;

public class CategoryScreenDTOMapper implements RowMapper<CategoryScreenDTO, Category> {
    @Override
    public CategoryScreenDTO mapRow(Category category) {
        try {
           return new CategoryScreenDTO(category.getId(), category.getLevel(), category.getName(), category.getIcon(), category.getPath());
        } catch (Exception ex) {
            return null;
        }
    }

    @Override
    public CategoryScreenDTO mapRow(Category category, IImageDataService repository) {
        return null;
    }
}
