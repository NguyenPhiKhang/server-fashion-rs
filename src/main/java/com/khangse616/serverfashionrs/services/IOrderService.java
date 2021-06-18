package com.khangse616.serverfashionrs.services;

import com.khangse616.serverfashionrs.models.Order;
import com.khangse616.serverfashionrs.models.dto.InputOrderDTO;

import java.util.List;

public interface IOrderService {
    void createOrder(InputOrderDTO orderInput);
    List<Order> getListOrderByStatus(int userId, int status);
    void updateStatusOfOrder(int orderId, int status);
    Order getDetailOrder(int orderId);
}
