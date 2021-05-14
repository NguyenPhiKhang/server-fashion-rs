package com.khangse616.serverfashionrs.services;

import com.khangse616.serverfashionrs.models.Cart;

import java.util.List;

public interface ICartService {
    Cart addProductInCart(int userId, int productId, int productOptionId, int amount);
    String removeProductInCart(int cartId);
    Cart updateProductInCart(int cartId, int productOptionId, int amount);
    List<Cart> getListProductInCart(int userId);
}
