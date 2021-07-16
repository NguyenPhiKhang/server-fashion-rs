package com.khangse616.serverfashionrs.controllers;

import com.khangse616.serverfashionrs.models.dto.ProductItemDTO;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/default")
@CrossOrigin(value = {"http://localhost:3000", "https://adminfashion-shop.azurewebsites.net"})
public interface IFavoriteController {
    @PostMapping("/{userId}/{productId}/update-favorite")
    String createOrUpdateFavoriteOfUser(@PathVariable("userId") int userId, @PathVariable("productId") int productId);

    @GetMapping("/{userId}/get-favorite-products")
    List<ProductItemDTO> getListFavoriteProduct(@PathVariable("userId") int userId);
}
