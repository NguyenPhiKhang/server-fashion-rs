package com.khangse616.serverfashionrs.models.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.khangse616.serverfashionrs.models.Product;

import java.sql.Timestamp;
import java.util.List;

public class RatingDTO {
    private int id;
    private String userName;
    private String imageAvatar;
    private int customerId;
    private String comment;
    private int star;
    private String timeUpdated;
    private List<String> imageRating;
    private String size;
    private String color;

    public RatingDTO() {
    }

    @JsonProperty("rating_id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @JsonProperty("user_name")
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @JsonProperty("customer_id")
    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customer) {
        this.customerId = customer;
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

    @JsonProperty("time-updated")
    public String getTimeUpdated() {
        return timeUpdated;
    }

    public void setTimeUpdated(String timeUpdated) {
        this.timeUpdated = timeUpdated;
    }

    @JsonProperty("image-avatar")
    public String getImageAvatar() {
        return imageAvatar;
    }

    public void setImageAvatar(String imageAvatar) {
        this.imageAvatar = imageAvatar;
    }

    @JsonProperty("images-rating")
    public List<String> getImageRating() {
        return imageRating;
    }

    public void setImageRating(List<String> imageRating) {
        this.imageRating = imageRating;
    }

    @JsonProperty("size")
    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    @JsonProperty("color")
    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
