package com.khangse616.serverfashionrs.controllers;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/default")
public interface IFavoriteProductController {
    @PostMapping("/{userId}/{productId}/update-favorite")
    String updateFavoriteProduct(@PathVariable("userId") int userId, @PathVariable("productId") int productId);
}
