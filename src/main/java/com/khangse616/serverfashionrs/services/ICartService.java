package com.khangse616.serverfashionrs.services;

import com.khangse616.serverfashionrs.models.Cart;

public interface ICartService {
    Cart addProductInCart(int userId, int productId, int productOptionId, int amount);
    String removeProductInCart(int cartId);
    Cart updateProductInCart(int cartId, int productOptionId, int amount);
}
