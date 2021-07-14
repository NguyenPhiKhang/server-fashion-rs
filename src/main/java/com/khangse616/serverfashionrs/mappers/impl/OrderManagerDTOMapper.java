package com.khangse616.serverfashionrs.mappers.impl;

import com.khangse616.serverfashionrs.Utils.StringUtil;
import com.khangse616.serverfashionrs.mappers.RowMapper;
import com.khangse616.serverfashionrs.models.*;
import com.khangse616.serverfashionrs.models.dto.OrderCardDTO;
import com.khangse616.serverfashionrs.models.dto.OrderItemDTO;
import com.khangse616.serverfashionrs.models.dto.OrderManagerDTO;
import com.khangse616.serverfashionrs.services.IImageDataService;

import java.util.ArrayList;
import java.util.List;

public class OrderManagerDTOMapper implements RowMapper<OrderManagerDTO, Order> {
    @Override
    public OrderManagerDTO mapRow(Order order) {
        try {
            OrderManagerDTO orderManagerDTO = new OrderManagerDTO();
            orderManagerDTO.setId(order.getId());
            orderManagerDTO.setGrandPrice(order.getGrandTotal());
            orderManagerDTO.setStatusOrder(order.getStatus());
            orderManagerDTO.setCreatedAt(StringUtil.convertTimestampToString(order.getCreatedAt()));
            orderManagerDTO.setAddress(new AddressDTOMapper().mapRow(order.getAddress()));
            orderManagerDTO.setUser(order.getUser());

            return orderManagerDTO;
        } catch (Exception ex) {
            return null;
        }
    }

    @Override
    public OrderManagerDTO mapRow(Order order, IImageDataService repository) {
        return null;
    }
}
