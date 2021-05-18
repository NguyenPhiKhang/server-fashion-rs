package com.khangse616.serverfashionrs.services;

import com.khangse616.serverfashionrs.models.Cart;
import com.khangse616.serverfashionrs.models.CartItem;

import java.util.List;

public interface ICartItemService {
    CartItem getCartItemByProductOption(int cartId, int productOptionId);
    CartItem getCartItemByProductOptionIsNull(int cartId, int productId);
    boolean checkExistsCartItemById(int id);
    CartItem save(CartItem cartItem);
    CartItem save(int productId, int productOptionId, int quantity, Cart cart);
    List<CartItem> getCartItemByUser(int userId);
}
