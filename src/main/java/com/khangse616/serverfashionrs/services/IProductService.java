package com.khangse616.serverfashionrs.services;

import com.khangse616.serverfashionrs.models.Product;
import com.khangse616.serverfashionrs.models.dto.AttributeOptionDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IProductService {
    Product findProductByIdVisibleTrue(int id);
    Product findProductById(int id);
    List<Product> getAllProductVisibility();
    Page<Product> getProductsByCategoriesOrderByNew(List<Integer> idCategories, Pageable pageable);
    Page<Product> getProductsByCategoriesOrderByPopular(List<Integer> idCategories, Pageable pageable);
    void generateIdRatingStar();
    List<Product> productTopRating(int page);
    List<Product> productRecommendForUser(int userId);
}
