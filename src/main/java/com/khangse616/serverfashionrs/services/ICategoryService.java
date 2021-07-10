package com.khangse616.serverfashionrs.services;

import com.khangse616.serverfashionrs.models.Category;

import java.util.Collection;
import java.util.List;
import java.util.Set;

public interface ICategoryService {
    Set<Category> findCategoriesByParentId(int id);
    List<String> getAllNameCategories();
    String autoSetIconCategory(int idCategory);
    Category findCategoryById(int id);
    String addIconCategories(String[] arrIcon);
    List<Category> findAllCategories();
    List<Category> findCategoryByLevel(int level);
    void saveAll(List<Category> categories);
    String getPathCategory(int id);
//    Set<String> recommendSearch(String keyword);
}
