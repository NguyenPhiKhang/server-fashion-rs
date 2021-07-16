package com.khangse616.serverfashionrs.controllers;

import com.khangse616.serverfashionrs.models.Category;
import com.khangse616.serverfashionrs.models.dto.CategoryScreenDTO;
import com.khangse616.serverfashionrs.models.dto.CategoryDetailDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RequestMapping("/default")
@CrossOrigin(value = {"http://localhost:3000", "https://adminfashion-shop.azurewebsites.net"})
public interface ICategoryController {
    @GetMapping("/categories/{parentId}/sub-categories")
    ResponseEntity<Set<Category>> getCategoriesByParentCategory(@PathVariable("parentId") int parentId);

//    @GetMapping("/recommend-search")
//    ResponseEntity<Set<String>> getRecommendSearch(@RequestParam("keyword") String keyword);

    @PutMapping("/categories/create-path")
    String autoCreatePath();

    @PostMapping("/categories/auto-add-icon")
    String autoAddIconCategories(@RequestParam("id") int idCategory);

    @PostMapping("/categories/add-icon")
    String AddIconCategory();

    @GetMapping("/categories")
    List<Category> getCategoriesByLevel(@RequestParam("level") int level);

    @GetMapping("/category/{id}")
    Category getCategoryById(@PathVariable int id);

    @GetMapping("/category/{id}/get-detail")
    CategoryDetailDTO getPathCategory(@PathVariable int id);

    @GetMapping("/categories/get-all")
    List<CategoryScreenDTO> getAllCategories(@RequestParam(value = "p", defaultValue = "1") int page, @RequestParam(value = "p_size", defaultValue = "5") int pageSize);

    @GetMapping("/categories/count-record")
    int countCategories();
}
