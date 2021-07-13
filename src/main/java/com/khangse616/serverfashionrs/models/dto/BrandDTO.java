package com.khangse616.serverfashionrs.models.dto;

public class BrandDTO {
    private int id;
    private String name;
    private String icon;
    private int countProduct;

    public BrandDTO() {
    }

    public BrandDTO(int id, String name, String icon, int countProduct) {
        this.id = id;
        this.name = name;
        this.icon = icon;
        this.countProduct = countProduct;
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

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public int getCountProduct() {
        return countProduct;
    }

    public void setCountProduct(int countProduct) {
        this.countProduct = countProduct;
    }
}
