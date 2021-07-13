package com.khangse616.serverfashionrs.mappers.impl;

import com.khangse616.serverfashionrs.Utils.StringUtil;
import com.khangse616.serverfashionrs.mappers.RowMapper;
import com.khangse616.serverfashionrs.models.Category;
import com.khangse616.serverfashionrs.models.dto.CategoryDetailDTO;
import com.khangse616.serverfashionrs.services.IImageDataService;
import org.apache.commons.lang3.ArrayUtils;

public class CategoryDetailDTOMapper implements RowMapper<CategoryDetailDTO, Category> {
    @Override
    public CategoryDetailDTO mapRow(Category category) {
        try {
            CategoryDetailDTO categoryDetailDTO = new CategoryDetailDTO();
            categoryDetailDTO.setId(category.getId());
            categoryDetailDTO.setName(category.getName());
            boolean isSubPath = category.getCategories().size() > 0;
            categoryDetailDTO.setSubCategory(isSubPath);
            String pathCategory = category.getPath();
            String[] ids = pathCategory.split("/");
            String[] arrIdCat;
            if (isSubPath) {
                arrIdCat = StringUtil.removeElementInArray(ids, new int[]{0});
            } else {
                arrIdCat = StringUtil.removeElementInArray(ids, new int[]{0, ids.length - 1});
            }
            ArrayUtils.reverse(arrIdCat);
            categoryDetailDTO.setCategoryIds(arrIdCat);

            categoryDetailDTO.setIcon(category.getIcon());
            categoryDetailDTO.setLevel(category.getLevel());
            categoryDetailDTO.setPathIds(category.getPath());
            categoryDetailDTO.setPathUrl(category.getPathVarchar());

            return categoryDetailDTO;
        } catch (Exception ex) {
            return null;
        }
    }

    @Override
    public CategoryDetailDTO mapRow(Category category, IImageDataService repository) {
        return null;
    }
}
