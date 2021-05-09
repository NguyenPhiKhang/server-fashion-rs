package com.khangse616.serverfashionrs.controllers;

import com.khangse616.serverfashionrs.models.Category;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Set;

@RequestMapping("/default")
public interface ICategoryController {
    @GetMapping("/categories/{parentId}/sub-categories")
    ResponseEntity<Set<Category>> getCategoriesByParentCategory(@PathVariable("parentId") int parentId);
}
