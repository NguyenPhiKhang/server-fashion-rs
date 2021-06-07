package com.khangse616.serverfashionrs.services;

import com.khangse616.serverfashionrs.models.Category;

import java.util.Collection;
import java.util.List;
import java.util.Set;

public interface ICategoryService {
    Set<Category> findCategoriesByParentId(int id);
    List<String> getAllNameCategories();
//    Set<String> recommendSearch(String keyword);
}
