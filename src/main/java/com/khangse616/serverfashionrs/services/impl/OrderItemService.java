package com.khangse616.serverfashionrs.services.impl;

import com.khangse616.serverfashionrs.models.Order;
import com.khangse616.serverfashionrs.models.OrderItem;
import com.khangse616.serverfashionrs.models.dto.OrderItemDTO;
import com.khangse616.serverfashionrs.repositories.OrderItemRepository;
import com.khangse616.serverfashionrs.services.IOrderItemService;
import com.khangse616.serverfashionrs.services.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Random;

@Service
public class OrderItemService implements IOrderItemService {
    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private IProductService productService;

    @Override
    public boolean checkExistsOrderItem(int id) {
        return orderItemRepository.existsById(id);
    }

    @Override
    public void save(OrderItemDTO orderItemDTO, Order order) {
        OrderItem orderItem = new OrderItem();
        Random rd = new Random();
        int idOrderItem;
        do {
            idOrderItem = 100 + rd.nextInt(6000001);
        } while (orderItemRepository.existsById(idOrderItem));

        orderItem.setId(idOrderItem);
        orderItem.setPrice(orderItemDTO.getPrice());
        orderItem.setImageUrl(orderItemDTO.getImageUrl());
        orderItem.setQuantity(orderItemDTO.getQuantity());
        orderItem.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        orderItem.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
        orderItem.setOrder(order);
        orderItem.setProduct(productService.findProductById(orderItemDTO.getProductId()));
        orderItem.setProductOption(productService.findProductById(orderItemDTO.getProductId()));

        orderItemRepository.save(orderItem);
    }

    @Override
    public void save(OrderItem orderItem) {
        orderItemRepository.save(orderItem);
    }
}
