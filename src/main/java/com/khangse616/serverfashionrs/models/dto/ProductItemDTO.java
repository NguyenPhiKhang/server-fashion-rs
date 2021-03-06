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
    private float promotionPercent;
    private String brand;
    private int orderCount;
    private String imgUrl;
    private float percentStar;
    private int countRating;
    private int quantity;
    private String shortDescription;

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
    public float getPromotionPercent() {
        return promotionPercent;
    }

    public void setPromotionPercent(float promotionPercent) {
        this.promotionPercent = promotionPercent;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @JsonProperty("quantity")
    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @JsonProperty("short_description")
    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }
}
