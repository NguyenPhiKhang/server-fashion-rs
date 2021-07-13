package com.khangse616.serverfashionrs.models.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CategoryDetailDTO {
    private int id;
    private String name;
    private String[] categoryIds;
    private boolean subCategory;
    private String pathIds;
    private String pathUrl;
    private int level;
    private String icon;

    public CategoryDetailDTO() {
    }

    @JsonProperty("id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("category_ids")
    public String[] getCategoryIds() {
        return categoryIds;
    }

    public void setCategoryIds(String[] categoryIds) {
        this.categoryIds = categoryIds;
    }

    @JsonProperty("path")
    public String getPathIds() {
        return pathIds;
    }

    public void setPathIds(String pathIds) {
        this.pathIds = pathIds;
    }

    @JsonProperty("path_url")
    public String getPathUrl() {
        return pathUrl;
    }

    public void setPathUrl(String pathUrl) {
        this.pathUrl = pathUrl;
    }

    @JsonProperty("is_subcategory")
    public boolean isSubCategory() {
        return subCategory;
    }

    public void setSubCategory(boolean subCategory) {
        this.subCategory = subCategory;
    }

    @JsonProperty("level")
    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    @JsonProperty("icon")
    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
}
