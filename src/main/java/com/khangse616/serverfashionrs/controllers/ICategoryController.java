package com.khangse616.serverfashionrs.controllers;

import com.khangse616.serverfashionrs.models.Category;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RequestMapping("/default")
@CrossOrigin(value = {"http://localhost:3000"})
public interface ICategoryController {
    @GetMapping("/categories/{parentId}/sub-categories")
    ResponseEntity<Set<Category>> getCategoriesByParentCategory(@PathVariable("parentId") int parentId);

//    @GetMapping("/recommend-search")
//    ResponseEntity<Set<String>> getRecommendSearch(@RequestParam("keyword") String keyword);

    @PostMapping("/categories/auto-add-icon")
    String autoAddIconCategories(@RequestParam("id") int idCategory);
}
