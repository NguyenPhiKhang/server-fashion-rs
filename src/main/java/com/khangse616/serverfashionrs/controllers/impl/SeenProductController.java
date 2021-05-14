package com.khangse616.serverfashionrs.controllers.impl;

import com.khangse616.serverfashionrs.controllers.ISeenProductController;
import com.khangse616.serverfashionrs.services.ISeenProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class SeenProductController implements ISeenProductController {
    @Autowired
    private ISeenProductService seenProductService;

    @Override
    public String CreateOrUpdateSeenProduct(int userId, int productId) {
        seenProductService.CreateOrUpdateSeenProduct(userId, productId);
        return "Đã cập nhật";
    }
}
