package com.khangse616.serverfashionrs.models.dto;

import com.khangse616.serverfashionrs.models.Payment;
import com.khangse616.serverfashionrs.models.Shipping;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

public class DetailOrderDTO {
    private int id;
    private AddressDTO address;
    private Shipping shipping;
    private Payment payment;
    private BigDecimal grandPrice;
    private BigDecimal subTotal;
    private BigDecimal shippingFee;
    private BigDecimal discount;
    private String content;
    private Timestamp createdAt;
    private Timestamp updatedAt;
    private Timestamp payAt;
    List<OrderItemDTO> listItem;

    public DetailOrderDTO() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public AddressDTO getAddress() {
        return address;
    }

    public void setAddress(AddressDTO address) {
        this.address = address;
    }

    public Shipping getShipping() {
        return shipping;
    }

    public void setShipping(Shipping shipping) {
        this.shipping = shipping;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public BigDecimal getGrandPrice() {
        return grandPrice;
    }

    public void setGrandPrice(BigDecimal grandPrice) {
        this.grandPrice = grandPrice;
    }

    public BigDecimal getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(BigDecimal subTotal) {
        this.subTotal = subTotal;
    }

    public BigDecimal getShippingFee() {
        return shippingFee;
    }

    public void setShippingFee(BigDecimal shippingFee) {
        this.shippingFee = shippingFee;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Timestamp getPayAt() {
        return payAt;
    }

    public void setPayAt(Timestamp payAt) {
        this.payAt = payAt;
    }

    public List<OrderItemDTO> getListItem() {
        return listItem;
    }

    public void setListItem(List<OrderItemDTO> listItem) {
        this.listItem = listItem;
    }
}
