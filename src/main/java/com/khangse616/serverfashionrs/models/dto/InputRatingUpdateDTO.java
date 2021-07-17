package com.khangse616.serverfashionrs.models.dto;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public class InputRatingUpdateDTO {
    private String comment;
    private int star;
    private boolean incognito;
    List<MultipartFile> listFiles;

    public InputRatingUpdateDTO() {
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

    public boolean isIncognito() {
        return incognito;
    }

    public void setIncognito(boolean incognito) {
        this.incognito = incognito;
    }

    public List<MultipartFile> getListFiles() {
        return listFiles;
    }

    public void setListFiles(List<MultipartFile> listFiles) {
        this.listFiles = listFiles;
    }
}
