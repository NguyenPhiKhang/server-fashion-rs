package com.khangse616.serverfashionrs.services;

import com.khangse616.serverfashionrs.models.Product;
import com.khangse616.serverfashionrs.models.dto.AttributeOptionDTO;
import com.khangse616.serverfashionrs.models.dto.InputReviewProductDTO;
import com.khangse616.serverfashionrs.models.dto.ProductItemDTO;
import com.khangse616.serverfashionrs.models.dto.SearchProductDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;

public interface IProductService {
    Product findProductByIdVisibleTrue(int id);
    Product findProductById(int id);
    List<Product> getAllProductVisibility();
    List<Product> getAllProductVisibilityOrderByPromotionPercent();
    Page<Product> getProductsByCategoriesOrderByNew(List<Integer> idCategories, Pageable pageable);
    Page<Product> getProductsByCategoriesOrderByPopular(List<Integer> idCategories, Pageable pageable);
    void generateIdRatingStar();
    List<Product> productTopRating(int page);
    List<Product> productRecommendForUser(int userId);
    List<HashMap<String, Double>> calcContentBasedTest(String textTest);
    List<SearchProductDTO> getProductSearch(String search, IImageDataService imageDataService, int page);
    List<ProductItemDTO> getProductsSimilarity(int id, IImageDataService imageDataService, int page);
    List<ProductItemDTO> getProductsAlsoLike(int userId, IImageDataService imageDataService, int page, String sameFor);
    void reviewProduct(int userId, InputReviewProductDTO inputReview);
    Product save(Product product);
    Page<Product> getProductByCategoryOrderPopular(int id, Pageable pageable);
    List<Product> getProductFilter(String search, int status, List<Integer> listCategories, int page, int pageSize);
    int countProductFilter(String search, int status, List<Integer> listCategories);
    List<Integer> getListIdCategoriesOfProducts();
    int countProductByBrandIsNull();
}
