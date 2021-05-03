package com.khangse616.serverfashionrs.controllers;

import com.khangse616.serverfashionrs.models.Category;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface ICategoryController {
    ResponseEntity<List<Category>> getCategoriesByParentCategory(int parentId);
}
