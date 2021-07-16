package com.khangse616.serverfashionrs.controllers;

import com.khangse616.serverfashionrs.models.Cart;
import com.khangse616.serverfashionrs.models.dto.CartItemDTO;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/default")
@CrossOrigin(value = {"http://localhost:3000", "https://adminfashion-shop.azurewebsites.net"})
public interface ICartController {
    @PostMapping("/cart/{userId}/{productId}/add")
    String addProductIntoCart(@PathVariable("userId") int userId, @PathVariable("productId") int productId, @RequestParam("p_option") int productOptionId, @RequestParam("qty") int quantity);

    @DeleteMapping("/cart/{cartItemId}/remove")
    String removeProductInCart(@PathVariable int cartItemId);

    @PutMapping("/cart/{userId}/{cartItemId}/update")
    String updateProductInCart(@PathVariable("userId") int userId, @PathVariable("cartItemId") int cartItemId, @RequestParam("p_option") int productOptionId, @RequestParam("qty") int quantity);
}
