package com.khangse616.serverfashionrs.controllers;

import com.khangse616.serverfashionrs.models.Cart;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/default")
public interface ICartController {
    @PostMapping("/cart/{userId}/{productId}/add")
    Cart addProductIntoCart(@PathVariable("userId") int userId, @PathVariable("productId") int productId, @RequestParam("p_option") int productOptionId, @RequestParam("amount") int amount);

    @DeleteMapping("/cart/{cartId}/remove")
    String removeProductInCart(@PathVariable int cartId);

    @PutMapping("/cart/{cartId}/update")
    Cart updateProductInCart(@PathVariable int cartId, @RequestParam("p_option") int productOptionId, @RequestParam("amount") int amount);

}
