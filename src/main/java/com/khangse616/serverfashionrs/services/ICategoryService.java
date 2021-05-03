package com.khangse616.serverfashionrs.services;

import com.khangse616.serverfashionrs.models.Category;

import java.util.List;

public interface ICategoryService {
    List<Category> findCategoriesByParentId(int id);
}
