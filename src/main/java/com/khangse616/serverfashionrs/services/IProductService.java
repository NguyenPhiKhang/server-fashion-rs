package com.khangse616.serverfashionrs.services;

import com.khangse616.serverfashionrs.models.Product;
import com.khangse616.serverfashionrs.models.dto.AttributeOptionDTO;
import com.khangse616.serverfashionrs.models.dto.ProductItemDTO;
import com.khangse616.serverfashionrs.models.dto.SearchProductDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.HashMap;
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
    List<HashMap<String, Double>> calcContentBasedTest(String textTest);
    List<SearchProductDTO> getProductSearch(String search, IImageDataService imageDataService);
    List<ProductItemDTO> getProductsSimilarity(int id, IImageDataService imageDataService);
    List<ProductItemDTO> getProductsAlsoLike(int userId, IImageDataService imageDataService);
}
