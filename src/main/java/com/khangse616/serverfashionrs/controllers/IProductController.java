package com.khangse616.serverfashionrs.controllers;

import com.khangse616.serverfashionrs.models.Product;
import com.khangse616.serverfashionrs.models.RecommendRating;
import com.khangse616.serverfashionrs.models.dto.ProductDetailDTO;
import com.khangse616.serverfashionrs.models.dto.ProductItemDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/default")
public interface IProductController {
    @GetMapping("/product/{id}")
    ResponseEntity<ProductDetailDTO> getProductById(@PathVariable int id);

    @GetMapping("/cat/{idCategory}/products")
    ResponseEntity<List<ProductItemDTO>> getProductsByCategory(@PathVariable("idCategory") int idCategory, @RequestParam("filter") String filter, @RequestParam("p") int page);

    @PostMapping("/product/generate-id-rating-star")
    ResponseEntity<String> generateIdRatingStarForProduct();

    @GetMapping("/recommend/top-rating/{userId}")
    ResponseEntity<List<ProductItemDTO>> getProductTopRating(@PathVariable("userId") int userId, @RequestParam(value = "p", defaultValue = "1") int page);

    @PostMapping("/recommend/create-cosine-similarity")
    ResponseEntity<String> createCosineSimilarity();

    @PostMapping("/create-prediction-rating/{userId}")
    ResponseEntity<RecommendRating> recommend_product_for_user(@PathVariable("userId") int user_id);
}
