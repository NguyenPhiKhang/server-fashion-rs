package com.khangse616.serverfashionrs.controllers.impl;

import com.khangse616.serverfashionrs.controllers.ICartController;
import com.khangse616.serverfashionrs.models.Cart;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class CartController implements ICartController {

    @Override
    public Cart addProductIntoCart(int userId, int productId, int productOptionId, int amount) {
        return null;
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
