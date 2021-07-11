package com.khangse616.serverfashionrs.models.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CategoryScreenDTO {
    private int id;
    private int level;
    private String name;
    private String icon;
    private String path;

    public CategoryScreenDTO() {
    }

    public CategoryScreenDTO(int id, int level, String name, String icon, String path) {
        this.id = id;
        this.level = level;
        this.name = name;
        this.icon = icon;
        this.path = path;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
