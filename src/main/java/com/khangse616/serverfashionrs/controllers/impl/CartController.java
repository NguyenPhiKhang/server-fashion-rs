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
    public String addProductIntoCart(int userId, int productId, int productOptionId, int quantity) {
        cartService.addProductInCart(userId, productId, productOptionId, quantity);
        return "add success";
    }

    @Override
    public String removeProductInCart(int cartItemId) {
        return cartService.removeProductInCart(cartItemId);
    }

    @Override
    public String updateProductInCart(int userId, int cartItemId, int productOptionId, int quantity) {
        cartService.updateProductInCart(userId, cartItemId, productOptionId, quantity);
        return "update success";
    }
//
//    @Override
//    public List<CartItemDTO> getListProductInCart(int userId) {
//        return cartService.getListProductInCart(userId).stream().map(value->new CartItemDTOMapper().mapRow(value, imageDataService)).collect(Collectors.toList());
//    }
}
