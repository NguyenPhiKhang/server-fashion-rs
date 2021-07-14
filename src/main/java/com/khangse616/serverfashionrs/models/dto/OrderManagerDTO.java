package com.khangse616.serverfashionrs.models.dto;

import com.khangse616.serverfashionrs.models.StatusOrder;
import com.khangse616.serverfashionrs.models.User;

import java.math.BigDecimal;

public class OrderManagerDTO {
    private int id;
    private User user;
    private BigDecimal grandPrice;
    private StatusOrder statusOrder;
    private String createdAt;
    private AddressDTO address;

    public OrderManagerDTO() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public BigDecimal getGrandPrice() {
        return grandPrice;
    }

    public void setGrandPrice(BigDecimal grandPrice) {
        this.grandPrice = grandPrice;
    }

    public StatusOrder getStatusOrder() {
        return statusOrder;
    }

    public void setStatusOrder(StatusOrder statusOrder) {
        this.statusOrder = statusOrder;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public AddressDTO getAddress() {
        return address;
    }

    public void setAddress(AddressDTO address) {
        this.address = address;
    }
}
