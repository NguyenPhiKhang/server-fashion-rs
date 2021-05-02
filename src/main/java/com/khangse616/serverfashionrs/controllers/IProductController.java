package com.khangse616.serverfashionrs.controllers;

import com.khangse616.serverfashionrs.models.dto.ProductDetailDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

public interface IProductController {
    ResponseEntity<ProductDetailDTO> getProductById(@PathVariable int id);
}
