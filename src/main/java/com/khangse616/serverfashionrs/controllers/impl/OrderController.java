package com.khangse616.serverfashionrs.controllers.impl;


import com.khangse616.serverfashionrs.controllers.IOrderController;
import com.khangse616.serverfashionrs.models.Order;
import com.khangse616.serverfashionrs.services.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class OrderController implements IOrderController {
    @Autowired
    private IOrderService orderService;

    @Override
    public Order createOrder(Order order) {
        return null;
    }
}