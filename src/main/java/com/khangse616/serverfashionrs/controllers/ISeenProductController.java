package com.khangse616.serverfashionrs.controllers;

import com.khangse616.serverfashionrs.models.dto.ProductItemDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("/default")
public interface ISeenProductController {
    @PostMapping("/{userId}/{productId}/seen-product")
    String createOrUpdateSeenProduct(@PathVariable("userId") int userId, @PathVariable("productId") int productId);

    @GetMapping("/{userId}/seen-products")
    List<ProductItemDTO> getListSeenProduct(@PathVariable("userId") int userId);
}
