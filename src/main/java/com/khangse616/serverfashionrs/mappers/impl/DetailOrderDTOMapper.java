package com.khangse616.serverfashionrs.mappers.impl;

import com.khangse616.serverfashionrs.Utils.StringUtil;
import com.khangse616.serverfashionrs.mappers.RowMapper;
import com.khangse616.serverfashionrs.models.*;
import com.khangse616.serverfashionrs.models.dto.DetailOrderDTO;
import com.khangse616.serverfashionrs.models.dto.OrderCardDTO;
import com.khangse616.serverfashionrs.models.dto.OrderItemDTO;
import com.khangse616.serverfashionrs.services.IImageDataService;

import java.util.ArrayList;
import java.util.List;

public class DetailOrderDTOMapper implements RowMapper<DetailOrderDTO, Order> {
    @Override
    public DetailOrderDTO mapRow(Order order) {

        try {
            DetailOrderDTO detailOrderDTO = new DetailOrderDTO();
            detailOrderDTO.setId(order.getId());
            detailOrderDTO.setAddress(new AddressDTOMapper().mapRow(order.getAddress()));
            detailOrderDTO.setShipping(order.getShipping());
            detailOrderDTO.setPayment(order.getPaymentMethod());
            detailOrderDTO.setGrandPrice(order.getGrandTotal());
            detailOrderDTO.setSubTotal(order.getSubTotal());
            detailOrderDTO.setShippingFee(order.getShippingFee());
            detailOrderDTO.setDiscount(order.getDiscount());
            detailOrderDTO.setContent(order.getContent());
            detailOrderDTO.setCreatedAt(StringUtil.convertTimestampToString(order.getCreatedAt()));
            detailOrderDTO.setUpdatedAt(StringUtil.convertTimestampToString(order.getUpdatedAt()));
            detailOrderDTO.setPayAt(order.getPayAt());
            detailOrderDTO.setUser(order.getUser());
            detailOrderDTO.setStatusOrder(order.getStatus());

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

            detailOrderDTO.setListItem(orderItems);
            return detailOrderDTO;
        } catch (Exception ex) {
            return null;
        }
    }

    @Override
    public DetailOrderDTO mapRow(Order order, IImageDataService repository) {
        return null;
    }
}
