package com.khangse616.serverfashionrs.services.impl;

import com.khangse616.serverfashionrs.Utils.CategoryUtil;
import com.khangse616.serverfashionrs.Utils.RecommendSystemUtil;
import com.khangse616.serverfashionrs.mappers.impl.ProductItemDTOMapper;
import com.khangse616.serverfashionrs.models.Category;
import com.khangse616.serverfashionrs.models.Product;
import com.khangse616.serverfashionrs.models.dto.ProductItemDTO;
import com.khangse616.serverfashionrs.repositories.CategoryRepository;
import com.khangse616.serverfashionrs.services.ICategoryService;
import com.khangse616.serverfashionrs.services.IImageDataService;
import com.khangse616.serverfashionrs.services.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;


@Service
public class CategoryService implements ICategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private IProductService productService;

    @Autowired
    private IImageDataService imageDataService;

    @Override
    public Set<Category> findCategoriesByParentId(int id) {
        return categoryRepository.findByParentId(id);
    }

    @Override
    public List<String> getAllNameCategories() {
        return categoryRepository.getAllName();
    }

    @Override
    public String autoSetIconCategory(int idCategory) {
        Category category = categoryRepository.findCategoryById(idCategory);

        List<Category> listCategory = new ArrayList<>();
        CategoryUtil.getListSubCategory(category, listCategory);

        Pageable pageable = PageRequest.of(0, 1);

        listCategory.forEach(c->{
            Page<Product> pageProduct = productService.getProductByCategoryOrderPopular(c.getId(), pageable);
            Product product = pageProduct.getContent().get(0);
            ProductItemDTO productItemDTO = new ProductItemDTOMapper().mapRow(product, imageDataService);
            System.out.println("Category "+c.getId());
            System.out.println("Product "+ productItemDTO.getId());
            c.setIcon(productItemDTO.getImgUrl());
            categoryRepository.save(c);
        });

        return "xong";
    }

    @Override
    public Category findCategoryById(int id) {
        return categoryRepository.findCategoryById(id);
    }

    @Override
    public String addIconCategories(String[] arrIcon) {
        List<Category> categories = categoryRepository.getAllCategoryOrderById();

        for (int i =0 ;i< categories.size(); i++){
            categories.get(i).setIcon(arrIcon[i]);
//            categoryRepository.save(categories.get(i));
        }

        categoryRepository.saveAll(categories);

        return "Xong";
    }

    @Override
    public List<Category> findAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public List<Category> findAllCategoriesOrderByLevel() {
        return categoryRepository.findAllCategoriesOrderByLevel();
    }

    @Override
    public List<Category> findCategoryByLevel(int level) {
        return categoryRepository.findByLevel(level);
    }

    @Override
    public void saveAll(List<Category> categories) {
        categoryRepository.saveAll(categories);
    }

    @Override
    public String getPathCategory(int id) {
        return categoryRepository.findPathCategory(id);
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
