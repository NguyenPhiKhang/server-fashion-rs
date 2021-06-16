package com.khangse616.serverfashionrs.services;

import com.khangse616.serverfashionrs.models.dto.OrderDTO;

public interface IOrderService {
    void createOrder(OrderDTO orderInput);
}
