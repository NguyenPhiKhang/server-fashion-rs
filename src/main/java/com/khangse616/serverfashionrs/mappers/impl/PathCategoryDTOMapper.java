package com.khangse616.serverfashionrs.mappers.impl;

import com.khangse616.serverfashionrs.Utils.StringUtil;
import com.khangse616.serverfashionrs.mappers.RowMapper;
import com.khangse616.serverfashionrs.models.Category;
import com.khangse616.serverfashionrs.models.dto.PathCategoryDTO;
import com.khangse616.serverfashionrs.services.IImageDataService;
import org.apache.commons.lang3.ArrayUtils;

import java.util.Collections;

public class PathCategoryDTOMapper implements RowMapper<PathCategoryDTO, Category> {
    @Override
    public PathCategoryDTO mapRow(Category category) {
        try {
            PathCategoryDTO pathCategoryDTO = new PathCategoryDTO();
            pathCategoryDTO.setId(category.getId());
            pathCategoryDTO.setName(category.getName());
            boolean isSubPath = category.getCategories().size() > 0;
            pathCategoryDTO.setSubCategory(isSubPath);
            String pathCategory = category.getPath();
            String[] ids = pathCategory.split("/");
            String[] arrIdCat;
            if (isSubPath) {
                arrIdCat = StringUtil.removeElementInArray(ids, new int[]{0});
            } else {
                arrIdCat = StringUtil.removeElementInArray(ids, new int[]{0, ids.length - 1});
            }
            ArrayUtils.reverse(arrIdCat);
            pathCategoryDTO.setCategoryIds(arrIdCat);

            return pathCategoryDTO;
        } catch (Exception ex) {
            return null;
        }
    }

    @Override
    public PathCategoryDTO mapRow(Category category, IImageDataService repository) {
        return null;
    }
}
