package com.khangse616.serverfashionrs.controllers;

import com.khangse616.serverfashionrs.models.Product;
import com.khangse616.serverfashionrs.models.RecommendRating;
import com.khangse616.serverfashionrs.models.dto.AttributeOptionDTO;
import com.khangse616.serverfashionrs.models.dto.ProductDetailDTO;
import com.khangse616.serverfashionrs.models.dto.ProductItemDTO;
import com.khangse616.serverfashionrs.models.dto.RecommendSystem.RecommendForUser;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RequestMapping("/default")
public interface IProductController {
    @GetMapping("/product/{id}")
    ResponseEntity<ProductDetailDTO> getProductById(@PathVariable int id, @RequestParam(value = "user", defaultValue = "0") int userId);

    @GetMapping("/cat/{idCategory}/products")
    ResponseEntity<List<ProductItemDTO>> getProductsByCategory(@PathVariable("idCategory") int idCategory, @RequestParam(value = "filter", defaultValue = "popular") String filter, @RequestParam(value = "p", defaultValue = "1") int page);

    @PostMapping("/product/generate-id-rating-star")
    ResponseEntity<String> generateIdRatingStarForProduct();

    @GetMapping("/recommend/top-rating/{userId}")
    ResponseEntity<List<ProductItemDTO>> getProductTopRating(@PathVariable("userId") int userId, @RequestParam(value = "p", defaultValue = "1") int page);

    @PostMapping("/recommend/create-cosine-similarity")
    ResponseEntity<String> createCosineSimilarity();

    @PostMapping("/create-prediction-rating/{userId}")
    ResponseEntity<RecommendRating> recommend_product_for_user(@PathVariable("userId") int user_id);

    @GetMapping("/product/{productId}/all-option")
    ResponseEntity<AttributeOptionDTO> getAttributeOptionByProduct(@PathVariable("productId") int productId);

    @GetMapping("/test-recommend-movie")
    ResponseEntity<List<RecommendForUser>> recommend_movie_test();

    @GetMapping("/calc-tfidf")
    List<HashMap<String, Double>> calculationTFIDF();
}
