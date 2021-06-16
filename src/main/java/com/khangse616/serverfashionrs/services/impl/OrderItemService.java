package com.khangse616.serverfashionrs.services.impl;

import com.khangse616.serverfashionrs.models.CartItem;
import com.khangse616.serverfashionrs.models.Order;
import com.khangse616.serverfashionrs.models.OrderItem;
import com.khangse616.serverfashionrs.repositories.OrderItemRepository;
import com.khangse616.serverfashionrs.services.ICartItemService;
import com.khangse616.serverfashionrs.services.IOrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class OrderItemService implements IOrderItemService {
    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private ICartItemService cartItemService;

    @Override
    public boolean checkExistsOrderItem(int id) {
        return orderItemRepository.existsById(id);
    }

    @Override
    public void save(int cartItemId, Order order) {
        CartItem cartItem = cartItemService.getCartItemById(cartItemId);
        OrderItem orderItem = new OrderItem();
        Random rd = new Random();
        int idOrderItem;
        do {
            idOrderItem = 100 + rd.nextInt(6000001);
        } while (orderItemRepository.existsById(idOrderItem));

        orderItem.setId(idOrderItem);
//        orderItem.setPrice(cartItem.);
    }

    @Override
    public void save(OrderItem orderItem) {
        orderItemRepository.save(orderItem);
    }
}
