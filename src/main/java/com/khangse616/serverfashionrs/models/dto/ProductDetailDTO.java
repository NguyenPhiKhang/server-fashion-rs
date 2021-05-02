package com.khangse616.serverfashionrs.models.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.khangse616.serverfashionrs.models.OptionProductDecimal;
import com.khangse616.serverfashionrs.models.OptionProductInteger;
import com.khangse616.serverfashionrs.models.OptionProductVarchar;

import java.math.BigDecimal;
import java.util.List;

public class ProductDetailDTO {
    private int id;
    private String name;
    private String sku;
    private String description;
    private String shortDescription;
    private String highlight;
    private String typeId;
    private boolean active;
    private boolean visibility;
    private boolean promotion;
    private int orderCount;
    private int quantity;
    private boolean freeShip;
    private String category;
    private String brand;
    private String material;
    private String purpose;
    private String suitable_season;
    private String madeIn;
    private String price;
    private int promotionPercent;
    private boolean stockStatus;

    private List<AttributeDTO<OptionProductVarchar>> listAttributeVarchar;
    private List<AttributeDTO<OptionProductDecimal>> listAttributeDecimal;
    private List<AttributeDTO<OptionProductInteger>> listAttributeInteger;

    public ProductDetailDTO() {
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

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getHighlight() {
        return highlight;
    }

    public void setHighlight(String highlight) {
        this.highlight = highlight;
    }

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public boolean isVisibility() {
        return visibility;
    }

    public void setVisibility(boolean visibility) {
        this.visibility = visibility;
    }

    public boolean isPromotion() {
        return promotion;
    }

    public void setPromotion(boolean promotion) {
        this.promotion = promotion;
    }

    public int getOrderCount() {
        return orderCount;
    }

    public void setOrderCount(int orderCount) {
        this.orderCount = orderCount;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public boolean isFreeShip() {
        return freeShip;
    }

    public void setFreeShip(boolean freeShip) {
        this.freeShip = freeShip;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public String getSuitable_season() {
        return suitable_season;
    }

    public void setSuitable_season(String suitable_season) {
        this.suitable_season = suitable_season;
    }

    public String getMadeIn() {
        return madeIn;
    }

    public void setMadeIn(String madeIn) {
        this.madeIn = madeIn;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public int getPromotionPercent() {
        return promotionPercent;
    }

    public void setPromotionPercent(int promotionPercent) {
        this.promotionPercent = promotionPercent;
    }

    public boolean isStockStatus() {
        return stockStatus;
    }

    public void setStockStatus(boolean stockStatus) {
        this.stockStatus = stockStatus;
    }

    public List<AttributeDTO<OptionProductVarchar>> getListAttributeVarchar() {
        return listAttributeVarchar;
    }

    public void setListAttributeVarchar(List<AttributeDTO<OptionProductVarchar>> listAttributeVarchar) {
        this.listAttributeVarchar = listAttributeVarchar;
    }

    public List<AttributeDTO<OptionProductDecimal>> getListAttributeDecimal() {
        return listAttributeDecimal;
    }

    public void setListAttributeDecimal(List<AttributeDTO<OptionProductDecimal>> listAttributeDecimal) {
        this.listAttributeDecimal = listAttributeDecimal;
    }

    public List<AttributeDTO<OptionProductInteger>> getListAttributeInteger() {
        return listAttributeInteger;
    }

    public void setListAttributeInteger(List<AttributeDTO<OptionProductInteger>> listAttributeInteger) {
        this.listAttributeInteger = listAttributeInteger;
    }
}

