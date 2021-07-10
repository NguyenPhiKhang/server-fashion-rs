package com.khangse616.serverfashionrs.models.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.khangse616.serverfashionrs.models.*;

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
    private boolean freeShip;
    private Category category;
    private String categories;
    private Brand brand;
    private String material;
    private String purpose;
    private String suitable_season;
    private String style;
    private String madeIn;
    private boolean liked;
    private float promotionPercent;
    private int totalQuantity;
    private RatingProductDTO ratings;

    private List<AttributeDTO<OptionProductVarchar>> listAttributeVarchar;

    private List<OptionProductDTO> optionProductDTOList;

    private RatingStar ratingStar;

    public ProductDetailDTO() {
    }

    public RatingStar getRatingStar() {
        return ratingStar;
    }

    public void setRatingStar(RatingStar ratingStar) {
        this.ratingStar = ratingStar;
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

    public boolean isFreeShip() {
        return freeShip;
    }

    public void setFreeShip(boolean freeShip) {
        this.freeShip = freeShip;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
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

    public boolean isLiked() {
        return liked;
    }

    public void setLiked(boolean liked) {
        this.liked = liked;
    }

    public float getPromotionPercent() {
        return promotionPercent;
    }

    public void setPromotionPercent(float promotionPercent) {
        this.promotionPercent = promotionPercent;
    }

    @JsonProperty(value = "attributes")
    public List<AttributeDTO<OptionProductVarchar>> getListAttributeVarchar() {
        return listAttributeVarchar;
    }

    public void setListAttributeVarchar(List<AttributeDTO<OptionProductVarchar>> listAttributeVarchar) {
        this.listAttributeVarchar = listAttributeVarchar;
    }

    @JsonProperty(value ="option_products")
    public List<OptionProductDTO> getOptionProductDTOList() {
        return optionProductDTOList;
    }

    public void setOptionProductDTOList(List<OptionProductDTO> optionProductDTOList) {
        this.optionProductDTOList = optionProductDTOList;
    }

    public String getCategories() {
        return categories;
    }

    public void setCategories(String categories) {
        this.categories = categories;
    }

    @JsonProperty(value = "total_quantity")
    public int getTotalQuantity() {
        return totalQuantity;
    }

    public void setTotalQuantity(int totalQuantity) {
        this.totalQuantity = totalQuantity;
    }

    public RatingProductDTO getRatings() {
        return ratings;
    }

    public void setRatings(RatingProductDTO ratings) {
        this.ratings = ratings;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }
}

