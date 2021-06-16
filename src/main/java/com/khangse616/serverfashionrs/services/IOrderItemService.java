package com.khangse616.serverfashionrs.services;

import com.khangse616.serverfashionrs.models.Order;
import com.khangse616.serverfashionrs.models.OrderItem;

public interface IOrderItemService {
    boolean checkExistsOrderItem(int id);
    void save(int cartItemId, Order order);
    void save(OrderItem orderItem);
}
