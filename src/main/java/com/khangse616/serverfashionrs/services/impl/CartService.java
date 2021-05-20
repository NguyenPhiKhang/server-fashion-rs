package com.khangse616.serverfashionrs.services.impl;

import com.khangse616.serverfashionrs.models.Cart;
import com.khangse616.serverfashionrs.models.CartItem;
import com.khangse616.serverfashionrs.models.Product;
import com.khangse616.serverfashionrs.repositories.CartRepository;
import com.khangse616.serverfashionrs.services.ICartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

@Service
public class CartService implements ICartService {
    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ProductService productService;

    @Autowired
    private UserService userService;

    @Autowired
    private CartItemService cartItemService;

    @Override
    public void addProductInCart(int userId, int productId, int productOptionId, int quantity) {
        Cart cart = cartRepository.findCartByUserId(userId);

        if (cart != null) {
            CartItem cartItem = (productOptionId != 0) ? cartItemService.getCartItemByProductOption(cart.getId(), productOptionId)
                    : cartItemService.getCartItemByProductOptionIsNull(cart.getId(), productId);
            if (cartItem != null) {
                cartItem.setQuantity(cartItem.getQuantity() + 1);
                cartItem.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
            } else {
                cartItem = cartItemService.save(productId, productOptionId, quantity, cart);
                Set<CartItem> cartItems = cart.getCartItems();
                cartItems.add(cartItem);
                cart.setCartItems(cartItems);
            }
            cartRepository.save(cart);
        } else {
            cart = new Cart();
            Random rd = new Random();

            int idCart;
            do {
                idCart = 100 + rd.nextInt(6000001);
            } while (cartRepository.existsById(idCart));

            cart.setId(idCart);
            cart.setCreatedAt(new Timestamp(System.currentTimeMillis()));
            cart.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
            cart.setUser(userService.getUserById(userId));
            Cart cartSave = cartRepository.save(cart);

            cartItemService.save(productId, productOptionId, quantity, cartSave);
        }
    }

    @Override
    public String removeProductInCart(int cartItemId) {
        return cartItemService.removeCartItemById(cartItemId);
    }

    @Override
    public void updateProductInCart(int userId, int cartItemId, int productOptionId, int quantity) {
        CartItem cartItem = cartItemService.getCartItemById(cartItemId);
        CartItem cartItemOther = cartItemService.getCartItemOtherToMerge(userId, productOptionId, cartItemId);

        if (cartItemOther == null) {
            cartItem.setQuantity(quantity);
            Product productOpId = cartItem.getProductOption();
            if (productOpId != null && productOpId.getId() != productOptionId)
                cartItem.setProductOption(productService.findProductById(productOptionId));

            cartItem.setUpdatedAt(new Timestamp(System.currentTimeMillis()));

            cartItemService.save(cartItem);
        } else {
            cartItemOther.setQuantity(cartItemOther.getQuantity() + 1);
            cartItemOther.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
            cartItemService.save(cartItemOther);

            removeProductInCart(cartItemId);
        }
    }
}
