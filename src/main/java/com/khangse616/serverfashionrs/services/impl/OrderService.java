package com.khangse616.serverfashionrs.services.impl;

import com.khangse616.serverfashionrs.models.Order;
import com.khangse616.serverfashionrs.models.OrderItem;
import com.khangse616.serverfashionrs.models.dto.InputOrderDTO;
import com.khangse616.serverfashionrs.models.dto.InputOrderItemDTO;
import com.khangse616.serverfashionrs.repositories.OrderRepository;
import com.khangse616.serverfashionrs.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
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

    @Autowired
    private IShippingService shippingService;

    @Autowired
    private IPaymentService paymentService;

    @Autowired
    private IStatusOrderService statusOrderService;

    @Override
    public void createOrder(InputOrderDTO orderInput) {
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
        newOrder.setShipping(shippingService.getShippingById(orderInput.getShipping()));
        newOrder.setPaymentMethod(paymentService.getPaymentById(orderInput.getPaymentMethod()));
        newOrder.setStatus(statusOrderService.getStatusOrderById(orderInput.getStatus()));

        Order orderSave = orderRepository.save(newOrder);

        for (InputOrderItemDTO orderItemDTO : orderInput.getListItem()) {
            orderItemService.save(orderItemDTO, orderSave);
        }
    }

    @Override
    public List<Order> getListOrderByStatus(int userId, int status) {
        return orderRepository.findAllByUserIdAndStatusId(userId, status);
    }

    @Override
    public void updateStatusOfOrder(int orderId, int status) {
        Order order = orderRepository.findById(orderId).orElse(null);
        if(order == null) return;
        if (status == 5 || status == 6){
            for(OrderItem orderItem: order.getOrderItems()){
                orderItemService.updateQuantity(orderItem);
            }
        }

        if(status == 4)
            order.setPayAt(new Timestamp(System.currentTimeMillis()));

        order.setStatus(statusOrderService.getStatusOrderById(status));
        order.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
        orderRepository.save(order);
    }

    @Override
    public Order getDetailOrder(int orderId) {
        return orderRepository.findById(orderId).orElse(null);
    }
}
