package com.khangse616.serverfashionrs.models.dto;

import org.springframework.web.multipart.MultipartFile;

public class InputBrandDTO {
    private int id;
    private String name;
    private MultipartFile icon;

    public InputBrandDTO(int id, String name, MultipartFile icon) {
        this.id = id;
        this.name = name;
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

    public MultipartFile getIcon() {
        return icon;
    }

    public void setIcon(MultipartFile icon) {
        this.icon = icon;
    }
}
