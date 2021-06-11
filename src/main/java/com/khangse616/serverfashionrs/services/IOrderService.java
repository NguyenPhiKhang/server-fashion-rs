package com.khangse616.serverfashionrs.services;

import com.khangse616.serverfashionrs.models.Order;
import com.khangse616.serverfashionrs.models.dto.CreateOrderDTO;

public interface IOrderService {
    Order createOrder(CreateOrderDTO orderInput);
}
