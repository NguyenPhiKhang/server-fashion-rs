package com.khangse616.serverfashionrs.controllers;

import com.khangse616.serverfashionrs.models.Product;
import com.khangse616.serverfashionrs.models.dto.ProductDetailDTO;
import com.khangse616.serverfashionrs.models.dto.ProductItemDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface IProductController {
    ResponseEntity<ProductDetailDTO> getProductById(@PathVariable int id);
    public ResponseEntity<List<ProductItemDTO>> getProductsByCategory(@PathVariable("idCategory") int idCategory, @RequestParam("p") int page);
}
