package com.khangse616.serverfashionrs.controllers.impl;

import com.khangse616.serverfashionrs.controllers.IOrderItemController;
import com.khangse616.serverfashionrs.services.IOrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/v1")
public class OrderItemController implements IOrderItemController {
    @Autowired
    private IOrderItemService orderItemService;

    @Override
    public String updateReviewStatus(int id, int status) {
        orderItemService.updateIsReview(id, status);
        return "Cập nhật thành công!";
    }
}
