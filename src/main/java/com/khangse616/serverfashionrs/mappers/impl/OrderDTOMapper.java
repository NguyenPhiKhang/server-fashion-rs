package com.khangse616.serverfashionrs.mappers.impl;

import com.khangse616.serverfashionrs.Utils.StringUtil;
import com.khangse616.serverfashionrs.mappers.RowMapper;
import com.khangse616.serverfashionrs.models.*;
import com.khangse616.serverfashionrs.models.dto.OrderCardDTO;
import com.khangse616.serverfashionrs.models.dto.OrderItemDTO;
import com.khangse616.serverfashionrs.models.dto.RatingDTO;
import com.khangse616.serverfashionrs.services.IImageDataService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class OrderDTOMapper implements RowMapper<OrderCardDTO, Order> {
    @Override
    public OrderCardDTO mapRow(Order order) {
        try {
            OrderCardDTO orderCardDTO = new OrderCardDTO();
            orderCardDTO.setId(order.getId());
            orderCardDTO.setStatus(order.getStatus().getName());
            orderCardDTO.setShipping(order.getShipping().getName());
            orderCardDTO.setGrandPrice(order.getGrandTotal());
            List<OrderItemDTO> orderItems = new ArrayList<>();

            for (OrderItem orderItem : order.getOrderItems()) {
                OrderItemDTO orderItemDTO = new OrderItemDTO();
                orderItemDTO.setId(orderItem.getId());
                orderItemDTO.setPrice(orderItem.getPrice());
                orderItemDTO.setQuantity(orderItem.getQuantity());
                orderItemDTO.setImageUrl(orderItem.getImageUrl());
                Product product = orderItem.getProduct();
                orderItemDTO.setProductId(product.getId());
                orderItemDTO.setName(product.getName());
                orderItemDTO.setReviewStatus(orderItem.isReviewStatus());

                if (product.getTypeId().equals("configurable")) {
                    Product productOption = orderItem.getProductOption();
                    orderItemDTO.setProductOptionId(productOption.getId());

                    for (OptionProductVarchar optionProductVarchar : productOption.getOptionProductVarchars()) {
                        Attribute attribute = optionProductVarchar.getAttribute();
                        if (attribute.getId() == 80) {
                            orderItemDTO.setColor(optionProductVarchar.getValue());
                        } else {
                            if (attribute.getId() == 164)
                                orderItemDTO.setSize(optionProductVarchar.getValue());
                        }
                    }
                }

                orderItems.add(orderItemDTO);
            }

            orderCardDTO.setListItem(orderItems);

            return orderCardDTO;
        } catch (Exception ex) {
            return null;
        }
    }

    @Override
    public OrderCardDTO mapRow(Order order, IImageDataService repository) {
        return null;
    }
}
