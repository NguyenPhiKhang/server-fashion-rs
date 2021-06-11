package com.khangse616.serverfashionrs.controllers;

import com.khangse616.serverfashionrs.models.Order;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/default")
public interface IOrderController {
    @PostMapping("/order/create-order")
    Order createOrder(@RequestBody Order order);
}
