package com.khangse616.serverfashionrs.models.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CategoryScreenDTO {
    private int id;
    private int level;
    private String name;
    private String icon;

    public CategoryScreenDTO() {
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
}
