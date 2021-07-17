package com.khangse616.serverfashionrs.models.dto;

import java.util.List;

public class CardMyRatingDTO {
    private int id;
    private int userId;
    private String userName;
    private String imageAvatar;
    private int star;
    private String size;
    private String color;
    private int idProduct;
    private int idProductOption;
    private String nameProduct;
    private String imageProduct;
    private String comment;
    private List<FileRatingDTO> fileRating;
    private String timeUpdated;

    public CardMyRatingDTO() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getImageAvatar() {
        return imageAvatar;
    }

    public void setImageAvatar(String imageAvatar) {
        this.imageAvatar = imageAvatar;
    }

    public int getStar() {
        return star;
    }

    public void setStar(int star) {
        this.star = star;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getImageProduct() {
        return imageProduct;
    }

    public void setImageProduct(String imageProduct) {
        this.imageProduct = imageProduct;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public List<FileRatingDTO> getFileRating() {
        return fileRating;
    }

    public void setFileRating(List<FileRatingDTO> fileRating) {
        this.fileRating = fileRating;
    }

    public String getTimeUpdated() {
        return timeUpdated;
    }

    public void setTimeUpdated(String timeUpdated) {
        this.timeUpdated = timeUpdated;
    }

    public String getNameProduct() {
        return nameProduct;
    }

    public void setNameProduct(String nameProduct) {
        this.nameProduct = nameProduct;
    }

    public int getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
    }

    public int getIdProductOption() {
        return idProductOption;
    }

    public void setIdProductOption(int idProductOption) {
        this.idProductOption = idProductOption;
    }
}
