package com.khangse616.serverfashionrs.services.impl;

import com.khangse616.serverfashionrs.models.Order;
import com.khangse616.serverfashionrs.models.dto.CreateOrderDTO;
import com.khangse616.serverfashionrs.repositories.OrderRepository;
import com.khangse616.serverfashionrs.services.IAddressService;
import com.khangse616.serverfashionrs.services.IOrderItemService;
import com.khangse616.serverfashionrs.services.IOrderService;
import com.khangse616.serverfashionrs.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Random;

@Service
public class OrderService implements IOrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private IOrderItemService orderItemService;

    @Autowired
    private IAddressService addressService;

    @Autowired
    private IUserService userService;

//    @Autowired
//    private

    @Override
    public Order createOrder(CreateOrderDTO orderInput) {
        Order newOrder = new Order();

        Random rd = new Random();

        int idOrder;
        do {
            idOrder = 100 + rd.nextInt(6000001);
        } while (orderRepository.existsById(idOrder));

        newOrder.setId(idOrder);
        newOrder.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        newOrder.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
        newOrder.setAddress(addressService.getAddressById(orderInput.getAddressId()));
        newOrder.setUser(userService.getUserById(orderInput.getUserId()));
        newOrder.setSubTotal(orderInput.getSubTotal());
        newOrder.setShippingFee(orderInput.getShippingFee());
        newOrder.setGrandTotal(orderInput.getGrandTotal());
        newOrder.setDiscount(orderInput.getDiscount());
        newOrder.setContent(orderInput.getContent());
//        newOrder.setShipping();




        return null;
    }
}
