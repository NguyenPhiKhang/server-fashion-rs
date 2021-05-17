package com.khangse616.serverfashionrs.controllers;

import com.khangse616.serverfashionrs.models.Cart;
import com.khangse616.serverfashionrs.models.dto.CartItemDTO;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/default")
public interface ICartController {
    @PostMapping("/cart/{userId}/{productId}/add")
    String addProductIntoCart(@PathVariable("userId") int userId, @PathVariable("productId") int productId, @RequestParam("p_option") int productOptionId, @RequestParam("amount") int amount);

    @DeleteMapping("/cart/{cartId}/remove")
    String removeProductInCart(@PathVariable int cartId);

    @PutMapping("/cart/{cartId}/update")
    Cart updateProductInCart(@PathVariable("cartId") int cartId, @RequestParam("p_option") int productOptionId, @RequestParam("amount") int amount);

    @GetMapping("/cart/{userId}/get-list")
    List<CartItemDTO> getListProductInCart(@PathVariable("userId") int userId);

}
