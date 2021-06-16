package com.khangse616.serverfashionrs.services;

import com.khangse616.serverfashionrs.models.Order;
import com.khangse616.serverfashionrs.models.OrderItem;
import com.khangse616.serverfashionrs.models.dto.OrderItemDTO;

public interface IOrderItemService {
    boolean checkExistsOrderItem(int id);
    void save(OrderItemDTO orderItemDTO, Order order);
    void save(OrderItem orderItem);
}
