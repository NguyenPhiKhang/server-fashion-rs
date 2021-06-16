package com.khangse616.serverfashionrs.services;

import com.khangse616.serverfashionrs.models.dto.CreateOrderDTO;

public interface IOrderService {
    void createOrder(CreateOrderDTO orderInput);
}
