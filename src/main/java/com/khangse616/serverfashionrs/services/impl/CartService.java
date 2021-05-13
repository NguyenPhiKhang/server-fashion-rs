package com.khangse616.serverfashionrs.services.impl;

import com.khangse616.serverfashionrs.models.Cart;
import com.khangse616.serverfashionrs.repositories.CartRepository;
import com.khangse616.serverfashionrs.services.ICartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Random;

@Service
public class CartService implements ICartService {
    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ProductService productService;

    @Override
    public Cart addProductInCart(int userId, int productId, int productOptionId, int amount) {
        Cart cart = new Cart();
        Random rd = new Random();

        int idCart;
        do {
            idCart = 100 + rd.nextInt(6000001);
        } while (cartRepository.existsById(idCart));

        cart.setId(idCart);
        cart.setAmount(amount);
        cart.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        cart.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
        cart.setProduct(productService.findProductById(productId));
        cart.setProductOption(productService.findProductById(productOptionId));

        return null;
    }
}
