package com.khangse616.serverfashionrs.models.dto;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public class InputReviewProductDTO {
    private int orderItem;
    private String comment;
    private int star;
    private int incognito;
    private List<MultipartFile> listImage;

    public InputReviewProductDTO() {
    }

    public int getOrderItem() {
        return orderItem;
    }

    public void setOrderItem(int orderItem) {
        this.orderItem = orderItem;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getStar() {
        return star;
    }

    public void setStar(int star) {
        this.star = star;
    }

    public int getIncognito() {
        return incognito;
    }

    public void setIncognito(int incognito) {
        this.incognito = incognito;
    }

    public List<MultipartFile> getListImage() {
        return listImage;
    }

    public void setListImage(List<MultipartFile> listImage) {
        this.listImage = listImage;
    }
}
