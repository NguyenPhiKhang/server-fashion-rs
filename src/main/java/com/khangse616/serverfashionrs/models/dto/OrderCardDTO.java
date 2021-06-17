package com.khangse616.serverfashionrs.models.dto;

import java.math.BigDecimal;
import java.util.List;

public class OrderCardDTO {
    private int id;
    private String status;
    private String shipping;
    private BigDecimal grandPrice;
    private List<OrderItemDTO> listItem;

    public OrderCardDTO() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public BigDecimal getGrandPrice() {
        return grandPrice;
    }

    public void setGrandPrice(BigDecimal grandPrice) {
        this.grandPrice = grandPrice;
    }

    public List<OrderItemDTO> getListItem() {
        return listItem;
    }

    public void setListItem(List<OrderItemDTO> listItem) {
        this.listItem = listItem;
    }

    public String getShipping() {
        return shipping;
    }

    public void setShipping(String shipping) {
        this.shipping = shipping;
    }
}
