package com.khangse616.serverfashionrs.models.dto;

import java.util.List;

public class InputAddProductFlashSale {
    private int id;
    private int percent;
    private int quantity;
    private List<Integer> listProductId;

    public InputAddProductFlashSale() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPercent() {
        return percent;
    }

    public void setPercent(int percent) {
        this.percent = percent;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public List<Integer> getListProductId() {
        return listProductId;
    }

    public void setListProductId(List<Integer> listProductId) {
        this.listProductId = listProductId;
    }
}
