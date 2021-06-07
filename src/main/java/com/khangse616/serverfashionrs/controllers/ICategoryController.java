package com.khangse616.serverfashionrs.controllers;

import com.khangse616.serverfashionrs.models.Category;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Set;

@RequestMapping("/default")
public interface ICategoryController {
    @GetMapping("/categories/{parentId}/sub-categories")
    ResponseEntity<Set<Category>> getCategoriesByParentCategory(@PathVariable("parentId") int parentId);

    @GetMapping("/recommend-search")
    ResponseEntity<Set<String>> getRecommendSearch(@RequestParam("keyword") String keyword);
}
