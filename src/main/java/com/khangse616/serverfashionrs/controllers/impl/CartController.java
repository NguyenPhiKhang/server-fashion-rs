package com.khangse616.serverfashionrs.controllers.impl;

import com.khangse616.serverfashionrs.controllers.ICartController;
import com.khangse616.serverfashionrs.models.Cart;
import com.khangse616.serverfashionrs.services.ICartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class CartController implements ICartController {

    @Autowired
    private ICartService cartService;

    @Override
    public Cart addProductIntoCart(int userId, int productId, int productOptionId, int amount) {
        return cartService.addProductInCart(userId, productId, productOptionId, amount);
    }

    @Override
    public String removeProductInCart(int cartId) {
        return null;
    }

    @Override
    public Cart updateProductInCart(int cartId, int productOptionId, int amount) {
        return null;
    }
}
