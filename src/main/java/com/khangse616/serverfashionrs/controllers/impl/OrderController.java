package com.khangse616.serverfashionrs.controllers.impl;


import com.khangse616.serverfashionrs.controllers.IOrderController;
import com.khangse616.serverfashionrs.mappers.impl.DetailOrderDTOMapper;
import com.khangse616.serverfashionrs.mappers.impl.OrderDTOMapper;
import com.khangse616.serverfashionrs.mappers.impl.OrderManagerDTOMapper;
import com.khangse616.serverfashionrs.mappers.impl.ProductItemDTOMapper;
import com.khangse616.serverfashionrs.models.Order;
import com.khangse616.serverfashionrs.models.dto.DetailOrderDTO;
import com.khangse616.serverfashionrs.models.dto.InputOrderDTO;
import com.khangse616.serverfashionrs.models.dto.OrderCardDTO;
import com.khangse616.serverfashionrs.models.dto.OrderManagerDTO;
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

    @Override
    public String updateStatusOfOrder(int orderId, int status) {
        orderService.updateStatusOfOrder(orderId, status);
        return "Cập nhật thành công.";
    }

    @Override
    public DetailOrderDTO getDetailOrder(int orderId) {
        return new DetailOrderDTOMapper().mapRow(orderService.getDetailOrder(orderId));
    }

    @Override
    public List<OrderManagerDTO> getOrdersForAdmin(int userId, int status, String searchUser, int page, int pageSize) {
        return orderService.getOrdersFilterForAdmin(userId, status, searchUser, page, pageSize).stream()
                .map(value -> new OrderManagerDTOMapper().mapRow(value)).collect(Collectors.toList());
    }

    @Override
    public int countOrdersForAdmin(int userId, int status, String searchUser) {
        return orderService.countOrdersFilterForAdmin(userId, status, searchUser);
    }
}
