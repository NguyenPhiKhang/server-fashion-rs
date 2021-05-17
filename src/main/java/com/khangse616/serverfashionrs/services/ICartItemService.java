package com.khangse616.serverfashionrs.services;

import com.khangse616.serverfashionrs.models.Cart;
import com.khangse616.serverfashionrs.models.CartItem;

public interface ICartItemService {
    CartItem getCartItemByProductOption(int cartId, int productOptionId);
    boolean checkExistsCartItemById(int id);
    CartItem save(CartItem cartItem);
    CartItem save(int productId, int productOptionId, int quantity);
}
