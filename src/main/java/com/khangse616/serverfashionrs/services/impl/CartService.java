package com.khangse616.serverfashionrs.services.impl;

import com.khangse616.serverfashionrs.models.Cart;
import com.khangse616.serverfashionrs.models.CartItem;
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
            CartItem cartItem = (productOptionId!=0) ? cartItemService.getCartItemByProductOption(cart.getId(), productOptionId)
                    :cartItemService.getCartItemByProductOptionIsNull(cart.getId(), productId);
            if(cartItem!=null)
            {
                cartItem.setQuantity(cartItem.getQuantity()+1);
                cartItem.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
            }
            else{
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
    public String removeProductInCart(int cartId) {
        if (cartRepository.existsById(cartId)) {
            cartRepository.deleteById(cartId);
            return "Đã bỏ thành công sản phẩm khỏi giỏ hàng";
        } else {
            return "Không tồn tại product trong giỏ hàng";
        }
    }

    @Override
    public void updateProductInCart(int cartId, int productOptionId, int quantity) {
//        Cart cart = cartRepository.findCartByUserId(cartId);
//        if (cart != null) {
//            cart.setAmount(amount);
//            if (cart.getProductOption() == null || cart.getProductOption().getId() != productOptionId)
//                cart.setProductOption(productService.findProductById(productOptionId));
//
//            cart.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
//
//            cartRepository.save(cart);
//        }
    }

//    @Override
//    public List<Cart> getListProductInCart(int userId) {
//        Cart cart = cartRepository.findCartByUserId(userId);
//        Set<CartItem> cartItems = cart.getCartItems();
//        return cartRepository.findCartByUserIdOrderByUpdatedAtDesc(userId);
//    }
}
