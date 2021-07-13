package com.khangse616.serverfashionrs.models.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CategoryScreenDTO {
    private int id;
    private int level;
    private String name;
    private String icon;
    private String path;
    private String pathVarchar;
    private int parentId;

    public CategoryScreenDTO() {
    }

    public CategoryScreenDTO(int id, int level, String name, String icon, String path, String pathVarchar, int parentId) {
        this.id = id;
        this.level = level;
        this.name = name;
        this.icon = icon;
        this.path = path;
        this.pathVarchar = pathVarchar;
        this.parentId = parentId;
    }

    @JsonProperty("id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @JsonProperty("level")
    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("icon")
    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    @JsonProperty("path")
    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @JsonProperty("path_url")
    public String getPathVarchar() {
        return pathVarchar;
    }

    public void setPathVarchar(String pathVarchar) {
        this.pathVarchar = pathVarchar;
    }

    @JsonProperty("parent_id")
    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }
}
