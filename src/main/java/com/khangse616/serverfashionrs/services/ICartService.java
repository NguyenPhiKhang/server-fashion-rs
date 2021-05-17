package com.khangse616.serverfashionrs.services;

import com.khangse616.serverfashionrs.models.Cart;

import java.util.List;

public interface ICartService {
    void addProductInCart(int userId, int productId, int productOptionId, int quantity);
    String removeProductInCart(int cartId);
    void updateProductInCart(int cartId, int productOptionId, int quantity);
    List<Cart> getListProductInCart(int userId);
}
