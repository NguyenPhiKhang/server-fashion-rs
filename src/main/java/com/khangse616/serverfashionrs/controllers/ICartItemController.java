package com.khangse616.serverfashionrs.controllers;

import com.khangse616.serverfashionrs.models.dto.CartItemDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("/default")
public interface ICartItemController{
    @GetMapping("/cart/{userId}/get-list-item")
    List<CartItemDTO> getListProductInCart(@PathVariable("userId") int userId);
}
