package com.khangse616.serverfashionrs.controllers;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/default")
public interface ISeenProductController {
    @PostMapping("/{userId}/{productId}/seen-product")
    String CreateOrUpdateSeenProduct(@PathVariable("userId") int userId, @PathVariable("productId") int productId);
}
