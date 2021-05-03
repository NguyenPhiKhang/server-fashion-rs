package com.khangse616.serverfashionrs.services.impl;

import com.khangse616.serverfashionrs.models.Category;
import com.khangse616.serverfashionrs.repositories.CategoryRepository;
import com.khangse616.serverfashionrs.services.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService implements ICategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<Category> findCategoriesByParentId(int id) {
        return categoryRepository.findByParentId(id);
    }
}
