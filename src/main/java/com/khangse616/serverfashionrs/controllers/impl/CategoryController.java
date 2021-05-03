package com.khangse616.serverfashionrs.controllers.impl;

import com.khangse616.serverfashionrs.controllers.ICategoryController;
import com.khangse616.serverfashionrs.models.Category;
import com.khangse616.serverfashionrs.models.dto.CategoryScreenDTO;
import com.khangse616.serverfashionrs.services.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1")
public class CategoryController implements ICategoryController {
    @Autowired
    private ICategoryService categoryService;

    @GetMapping("/categories/{parentId}/sub-categories")
    @Override
    public ResponseEntity<Set<Category>> getCategoriesByParentCategory(@PathVariable("parentId") int parentId){
        Set<Category> categoryScreenDTO = categoryService.findCategoriesByParentId(parentId);
        return ResponseEntity.ok().body(categoryScreenDTO);
    }

//    @GetMapping("/categories")
//    public ResponseEntity<List<CategoryScreenDTO>> getCategoriesLevel1(@RequestParam(value = "level", required = false, defaultValue = "0") int level){
//        List<CategoryScreenDTO> categoryScreenDTO = level!=0?categoryService.findCategoryByLevel(level).stream().map(value->new CategoryScreenDTOMapper().mapRow(value)).collect(Collectors.toList())
//                : categoryService.findAllCategories().stream().map(value->new CategoryScreenDTOMapper().mapRow(value)).collect(Collectors.toList());
//        return ResponseEntity.ok().body(categoryScreenDTO);
//    }


}
