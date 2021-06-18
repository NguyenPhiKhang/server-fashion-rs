package com.khangse616.serverfashionrs.services.impl;

import com.khangse616.serverfashionrs.models.OptionProductInteger;
import com.khangse616.serverfashionrs.models.Order;
import com.khangse616.serverfashionrs.models.OrderItem;
import com.khangse616.serverfashionrs.models.Product;
import com.khangse616.serverfashionrs.models.dto.InputOrderItemDTO;
import com.khangse616.serverfashionrs.repositories.OrderItemRepository;
import com.khangse616.serverfashionrs.services.IOptionProductIntegerService;
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

    @Autowired
    private IOptionProductIntegerService optionProductIntegerService;

    @Override
    public boolean checkExistsOrderItem(int id) {
        return orderItemRepository.existsById(id);
    }

    @Override
    public void save(InputOrderItemDTO orderItemDTO, Order order) {
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
//        orderItem.setProduct(productService.findProductById(orderItemDTO.getProductId()));

        Product product = productService.findProductById(orderItemDTO.getProductId());
        if(product.getTypeId().equals("configurable")){
            Product productOption = productService.findProductById(orderItemDTO.getProductOptionId());
            OptionProductInteger optionProductInteger = optionProductIntegerService.getOptionProductIntegerByProductId(orderItemDTO.getProductOptionId());
            optionProductInteger.setValue(optionProductInteger.getValue() - orderItemDTO.getQuantity());
            optionProductIntegerService.save(optionProductInteger);
//            productOption.getOptionProductIntegers().forEach(v-> v.setValue(v.getValue()-orderItemDTO.getQuantity()));

            orderItem.setProductOption(productOption);
        }
        else{
            OptionProductInteger optionProductInteger = optionProductIntegerService.getOptionProductIntegerByProductId(orderItemDTO.getProductId());
            optionProductInteger.setValue(optionProductInteger.getValue() - orderItemDTO.getQuantity());
            optionProductIntegerService.save(optionProductInteger);
        }
        orderItem.setProduct(product);

        orderItemRepository.save(orderItem);
    }

    @Override
    public void save(OrderItem orderItem) {
        orderItemRepository.save(orderItem);
    }

    @Override
    public void updateQuantity(OrderItem orderItem) {
        Product product = orderItem.getProduct();
        if(product.getTypeId().equals("configurable")){
            Product productOption = orderItem.getProductOption();
            OptionProductInteger optionProductInteger = optionProductIntegerService.getOptionProductIntegerByProductId(productOption.getId());
            optionProductInteger.setValue(optionProductInteger.getValue() + orderItem.getQuantity());
            optionProductIntegerService.save(optionProductInteger);
//            productOption.getOptionProductIntegers().forEach(v-> v.setValue(v.getValue()-orderItemDTO.getQuantity()));
        }
        else{
            OptionProductInteger optionProductInteger = optionProductIntegerService.getOptionProductIntegerByProductId(product.getId());
            optionProductInteger.setValue(optionProductInteger.getValue() + orderItem.getQuantity());
            optionProductIntegerService.save(optionProductInteger);
        }
    }
}
