package com.khangse616.serverfashionrs.controllers.impl;


import com.khangse616.serverfashionrs.controllers.IOrderController;
import com.khangse616.serverfashionrs.models.dto.OrderDTO;
import com.khangse616.serverfashionrs.services.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class OrderController implements IOrderController {
    @Autowired
    private IOrderService orderService;

    @Override
    public String createOrder(OrderDTO orderInput) {
        orderService.createOrder(orderInput);
        return "Tạo đơn hàng thành công";
    }

    @Override
    public List<OrderDTO> getListOrderByStatus(int userId, int status) {
        return null;
    }
}
