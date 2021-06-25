package com.khangse616.serverfashionrs.models.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.khangse616.serverfashionrs.models.ImageData;
import com.khangse616.serverfashionrs.models.Product;

import java.sql.Timestamp;
import java.util.List;

public class RatingDTO {
    private int id;
    private int userId;
    private String userName;
    private String imageAvatar;
    private int star;
    private String size;
    private String color;
    private String comment;
    private List<FileRatingDTO> fileRating;
    private String timeUpdated;


    public RatingDTO() {
    }

    @JsonProperty("rating_id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @JsonProperty("user_id")
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @JsonProperty("user_name")
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @JsonProperty("image_avatar")
    public String getImageAvatar() {
        return imageAvatar;
    }

    public void setImageAvatar(String imageAvatar) {
        this.imageAvatar = imageAvatar;
    }

    @JsonProperty("star")
    public int getStar() {
        return star;
    }

    public void setStar(int star) {
        this.star = star;
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

    @JsonProperty("comment")
    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @JsonProperty("file_rating")
    public List<FileRatingDTO> getFileRating() {
        return fileRating;
    }

    public void setFileRating(List<FileRatingDTO> fileRating) {
        this.fileRating = fileRating;
    }

    @JsonProperty("time_updated")
    public String getTimeUpdated() {
        return timeUpdated;
    }

    public void setTimeUpdated(String timeUpdated) {
        this.timeUpdated = timeUpdated;
    }
}
