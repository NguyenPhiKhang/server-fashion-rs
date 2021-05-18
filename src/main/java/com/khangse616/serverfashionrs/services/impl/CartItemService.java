package com.khangse616.serverfashionrs.services.impl;

import com.khangse616.serverfashionrs.models.Cart;
import com.khangse616.serverfashionrs.models.CartItem;
import com.khangse616.serverfashionrs.repositories.CartItemRepository;
import com.khangse616.serverfashionrs.services.ICartItemService;
import com.khangse616.serverfashionrs.services.ICartService;
import com.khangse616.serverfashionrs.services.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Random;

@Service
public class CartItemService implements ICartItemService {
    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private IProductService productService;

    @Autowired
    private ICartService cartService;

    @Override
    public CartItem getCartItemByProductOption(int cartId, int productOptionId) {
        return cartItemRepository.findCartItemByCartIdAndProductOptionId(cartId, productOptionId);
    }

    @Override
    public CartItem getCartItemByProductOptionIsNull(int cartId, int productId) {
        return cartItemRepository.findCartItemByCartIdAndProductIdAndProductOptionIsNull(cartId, productId);
    }

    @Override
    public boolean checkExistsCartItemById(int id) {
        return cartItemRepository.existsCartItemById(id);
    }

    @Override
    public CartItem save(CartItem cartItem) {
        return cartItemRepository.save(cartItem);
    }

    @Override
    public CartItem save(int productId, int productOptionId, int quantity, Cart cart) {
        CartItem cartItem = new CartItem();
        Random rd = new Random();
        int idCartItem;
        do {
            idCartItem = 100 + rd.nextInt(6000001);
        } while (cartItemRepository.existsCartItemById(idCartItem));

        cartItem.setId(idCartItem);
        cartItem.setQuantity(quantity);
        cartItem.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        cartItem.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
        cartItem.setProduct(productService.findProductByIdVisibleTrue(productId));
        cartItem.setProductOption(productService.findProductById(productOptionId));
        cartItem.setCart(cart);

        return cartItemRepository.save(cartItem);
    }

    @Override
    public List<CartItem> getCartItemByUser(int userId) {
        return cartItemRepository.findCartItemByUserId(userId);
    }
}
