package com.khangse616.serverfashionrs.models.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.khangse616.serverfashionrs.models.Category;

import java.math.BigDecimal;

public class ProductItemDTO {
    private int id;
    private String name;
    private Category category;
    private boolean freeShip;
    private PriceResultDTO price;
    private int promotionPercent;
    private int orderCount;
    private String imgUrl;
    private float percentStar;
    private int countRating;

    public ProductItemDTO() {
    }

    @JsonProperty("percent_star")
    public float getPercentStar() {
        return percentStar;
    }

    public void setPercentStar(float percentStar) {
        this.percentStar = percentStar;
    }

    @JsonProperty("count_rating")
    public int getCountRating() {
        return countRating;
    }

    public void setCountRating(int countRating) {
        this.countRating = countRating;
    }

    @JsonProperty("img_url")
    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    @JsonProperty("order_count")
    public int getOrderCount() {
        return orderCount;
    }

    public void setOrderCount(int orderCount) {
        this.orderCount = orderCount;
    }

    @JsonProperty("id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("free_shipping")
    public boolean isFreeShip() {
        return freeShip;
    }

    public void setFreeShip(boolean freeShip) {
        this.freeShip = freeShip;
    }

    public PriceResultDTO getPrice() {
        return price;
    }

    public void setPrice(PriceResultDTO price) {
        this.price = price;
    }

    @JsonProperty("promotion_percent")
    public int getPromotionPercent() {
        return promotionPercent;
    }

    public void setPromotionPercent(int promotionPercent) {
        this.promotionPercent = promotionPercent;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
