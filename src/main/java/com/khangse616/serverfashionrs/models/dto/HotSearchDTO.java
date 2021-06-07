package com.khangse616.serverfashionrs.models.dto;

public class HotSearchDTO {
    private String keyword;
    private String linkImage;

    public HotSearchDTO() {
    }

    public HotSearchDTO(String keyword, String linkImage) {
        this.keyword = keyword;
        this.linkImage = linkImage;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getLinkImage() {
        return linkImage;
    }

    public void setLinkImage(String linkImage) {
        this.linkImage = linkImage;
    }
}
