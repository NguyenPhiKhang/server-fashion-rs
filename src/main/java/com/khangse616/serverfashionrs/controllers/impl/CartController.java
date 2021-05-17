package com.khangse616.serverfashionrs.controllers.impl;

import com.khangse616.serverfashionrs.controllers.ICartController;
import com.khangse616.serverfashionrs.mappers.impl.CartItemDTOMapper;
import com.khangse616.serverfashionrs.models.Cart;
import com.khangse616.serverfashionrs.models.dto.CartItemDTO;
import com.khangse616.serverfashionrs.services.ICartService;
import com.khangse616.serverfashionrs.services.IImageDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1")
public class CartController implements ICartController {

    @Autowired
    private ICartService cartService;

    @Autowired
    private IImageDataService imageDataService;

    @Override
    public String addProductIntoCart(int userId, int productId, int productOptionId, int amount) {
        cartService.addProductInCart(userId, productId, productOptionId, amount);
        return "success";
    }

    @Override
    public String removeProductInCart(int cartId) {
        return cartService.removeProductInCart(cartId);
    }

    @Override
    public Cart updateProductInCart(int cartId, int productOptionId, int amount) {
        return cartService.updateProductInCart(cartId, productOptionId, amount);
    }

    @Override
    public List<CartItemDTO> getListProductInCart(int userId) {
        return cartService.getListProductInCart(userId).stream().map(value->new CartItemDTOMapper().mapRow(value, imageDataService)).collect(Collectors.toList());
    }
}
