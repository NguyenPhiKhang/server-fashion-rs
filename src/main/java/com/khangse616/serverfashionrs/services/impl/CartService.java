package com.khangse616.serverfashionrs.services.impl;

import com.khangse616.serverfashionrs.models.Cart;
import com.khangse616.serverfashionrs.repositories.CartRepository;
import com.khangse616.serverfashionrs.services.ICartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Random;

@Service
public class CartService implements ICartService {
    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ProductService productService;

    @Autowired
    private UserService userService;

    @Override
    public Cart addProductInCart(int userId, int productId, int productOptionId, int amount) {
        Cart cart = cartRepository.findCartByUserIdAndProductIdAndProductOptionId(userId, productId, productOptionId);

        if (cart != null) {
           cart.setAmount(cart.getAmount() + amount);
           cart.setUpdatedAt(new Timestamp(System.currentTimeMillis()));

        } else {
            cart = new Cart();
            Random rd = new Random();

            int idCart;
            do {
                idCart = 100 + rd.nextInt(6000001);
            } while (cartRepository.existsById(idCart));

            cart.setId(idCart);
            cart.setAmount(amount);
            cart.setCreatedAt(new Timestamp(System.currentTimeMillis()));
            cart.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
            cart.setProduct(productService.findProductByIdVisibleTrue(productId));
            cart.setProductOption(productService.findProductById(productOptionId));
            cart.setUser(userService.getUserById(userId));
        }

        return cartRepository.save(cart);
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
    public Cart updateProductInCart(int cartId, int productOptionId, int amount) {
        Cart cart = cartRepository.findById(cartId).orElse(null);
        if (cart != null) {
            cart.setAmount(amount);
            if (cart.getProductOption() == null || cart.getProductOption().getId() != productOptionId)
                cart.setProductOption(productService.findProductById(productOptionId));

            cart.setUpdatedAt(new Timestamp(System.currentTimeMillis()));

            return cartRepository.save(cart);
        }
        return null;
    }

    @Override
    public List<Cart> getListProductInCart(int userId) {
        return cartRepository.findCartByUserIdOrderByUpdatedAtDesc(userId);
    }
}
