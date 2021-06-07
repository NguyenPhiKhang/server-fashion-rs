package com.khangse616.serverfashionrs.services.impl;

import com.khangse616.serverfashionrs.Utils.RecommendSystemUtil;
import com.khangse616.serverfashionrs.mappers.impl.ProductItemDTOMapper;
import com.khangse616.serverfashionrs.models.Category;
import com.khangse616.serverfashionrs.models.Product;
import com.khangse616.serverfashionrs.repositories.CategoryRepository;
import com.khangse616.serverfashionrs.services.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class CategoryService implements ICategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Set<Category> findCategoriesByParentId(int id) {
        return categoryRepository.findByParentId(id);
    }

    @Override
    public List<String> getAllNameCategories() {
        return categoryRepository.getAllName();
    }

//    @Override
//    public Set<String> recommendSearch(String keyword) {
//        Set<String> newWord = new HashSet<>();
//        getAllNameCategories().forEach(c-> {
//            newWord.addAll(Arrays.asList(c.split(" - ")));
//        });
//
//        return RecommendSystemUtil.calcCosineSimilaritySearch(keyword, new ArrayList<>(newWord)).entrySet().stream()
//                .sorted(Map.Entry.<String, Double>comparingByValue().reversed())
//                .limit(10)
//                .map(Map.Entry::getKey).collect(Collectors.toSet());
//    }
}
