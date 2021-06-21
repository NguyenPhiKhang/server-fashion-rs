package com.khangse616.serverfashionrs.models.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public class InputReviewProductDTO {
    private int orderItem;
    private String comment;
    private int star;
    private boolean incognito;
    private List<MultipartFile> listImage;

    public InputReviewProductDTO() {
    }

    @JsonProperty("order-item")
    public int getOrderItem() {
        return orderItem;
    }

    public void setOrderItem(int orderItem) {
        this.orderItem = orderItem;
    }

    @JsonProperty("comment")
    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @JsonProperty("star")
    public int getStar() {
        return star;
    }

    public void setStar(int star) {
        this.star = star;
    }

    @JsonProperty("incognito")
    public boolean getIncognito() {
        return incognito;
    }

    public void setIncognito(boolean incognito) {
        this.incognito = incognito;
    }

    @JsonProperty("list-image")
    public List<MultipartFile> getListImage() {
        return listImage;
    }

    public void setListImage(List<MultipartFile> listImage) {
        this.listImage = listImage;
    }
}
