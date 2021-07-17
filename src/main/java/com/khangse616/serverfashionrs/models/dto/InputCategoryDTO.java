package com.khangse616.serverfashionrs.models.dto;

import org.springframework.web.multipart.MultipartFile;

public class InputCategoryDTO {
    private int id;
    private String name;
    private String path;
    private int parentId;
    private MultipartFile icon;

    public InputCategoryDTO() {
    }

    public InputCategoryDTO(String name, String path, int parentId, MultipartFile icon) {
        this.name = name;
        this.path = path;
        this.parentId = parentId;
        this.icon = icon;
    }

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

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public MultipartFile getIcon() {
        return icon;
    }

    public void setIcon(MultipartFile icon) {
        this.icon = icon;
    }
}
