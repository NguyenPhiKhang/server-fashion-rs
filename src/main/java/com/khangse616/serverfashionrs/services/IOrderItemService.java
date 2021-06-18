package com.khangse616.serverfashionrs.services;

import com.khangse616.serverfashionrs.models.Order;
import com.khangse616.serverfashionrs.models.OrderItem;
import com.khangse616.serverfashionrs.models.dto.InputOrderItemDTO;

public interface IOrderItemService {
    boolean checkExistsOrderItem(int id);
    void save(InputOrderItemDTO orderItemDTO, Order order);
    void save(OrderItem orderItem);
    void updateQuantity(OrderItem orderItem);
}
