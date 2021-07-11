package com.khangse616.serverfashionrs.models.dto;

public class PathCategoryDTO {
    private int id;
    private String name;
    private String[] categoryIds;
    private boolean subCategory;
    private String pathIds;
    private String pathUrl;

    public PathCategoryDTO() {
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

    public String[] getCategoryIds() {
        return categoryIds;
    }

    public void setCategoryIds(String[] categoryIds) {
        this.categoryIds = categoryIds;
    }

    public String getPathIds() {
        return pathIds;
    }

    public void setPathIds(String pathIds) {
        this.pathIds = pathIds;
    }

    public String getPathUrl() {
        return pathUrl;
    }

    public void setPathUrl(String pathUrl) {
        this.pathUrl = pathUrl;
    }

    public boolean isSubCategory() {
        return subCategory;
    }

    public void setSubCategory(boolean subCategory) {
        this.subCategory = subCategory;
    }
}
