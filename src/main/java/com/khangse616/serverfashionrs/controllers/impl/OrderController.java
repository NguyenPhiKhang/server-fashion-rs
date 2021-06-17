package com.khangse616.serverfashionrs.controllers.impl;


import com.khangse616.serverfashionrs.controllers.IOrderController;
import com.khangse616.serverfashionrs.mappers.impl.OrderDTOMapper;
import com.khangse616.serverfashionrs.mappers.impl.ProductItemDTOMapper;
import com.khangse616.serverfashionrs.models.dto.InputOrderDTO;
import com.khangse616.serverfashionrs.models.dto.OrderCardDTO;
import com.khangse616.serverfashionrs.services.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1")
public class OrderController implements IOrderController {
    @Autowired
    private IOrderService orderService;

    @Override
    public String createOrder(InputOrderDTO orderInput) {
        orderService.createOrder(orderInput);
        return "Tạo đơn hàng thành công";
    }

    @Override
    public List<OrderCardDTO> getListOrderByStatus(int userId, int status) {
        return orderService.getListOrderByStatus(userId, status).stream()
                .map(value -> new OrderDTOMapper().mapRow(value)).collect(Collectors.toList());
    }
}
