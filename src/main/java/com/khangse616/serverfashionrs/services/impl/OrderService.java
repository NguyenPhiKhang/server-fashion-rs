package com.khangse616.serverfashionrs.services.impl;

import com.khangse616.serverfashionrs.models.Order;
import com.khangse616.serverfashionrs.repositories.OrderRepository;
import com.khangse616.serverfashionrs.services.IOrderItemService;
import com.khangse616.serverfashionrs.services.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class OrderService implements IOrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private IOrderItemService orderItemService;

    @Override
    public Order createOrder(Order order) {
        Order newOrder = new Order();

        Random rd = new Random();

        int idOrder;
        do {
            idOrder = 100 + rd.nextInt(6000001);
        } while (orderRepository.existsById(idOrder));






        return null;
    }
}
