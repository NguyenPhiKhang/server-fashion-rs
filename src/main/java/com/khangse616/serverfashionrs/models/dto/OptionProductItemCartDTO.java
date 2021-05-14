package com.khangse616.serverfashionrs.models.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.khangse616.serverfashionrs.models.OptionProductDecimal;
import com.khangse616.serverfashionrs.models.OptionProductInteger;
import com.khangse616.serverfashionrs.models.OptionProductVarchar;

public class OptionProductItemCartDTO {
    private int productOptionId;
    private OptionProductDecimal price;
    private OptionProductInteger quantity;
    private OptionProductVarchar color;
    private OptionProductVarchar size;
    private OptionProductVarchar image;

    public OptionProductItemCartDTO() {
    }

    @JsonProperty("product-option-id")
    public int getProductOptionId() {
        return productOptionId;
    }

    public void setProductOptionId(int productOptionId) {
        this.productOptionId = productOptionId;
    }

    @JsonProperty("price")
    public OptionProductDecimal getPrice() {
        return price;
    }

    public void setPrice(OptionProductDecimal price) {
        this.price = price;
    }

    @JsonProperty("quantity")
    public OptionProductInteger getQuantity() {
        return quantity;
    }

    public void setQuantity(OptionProductInteger quantity) {
        this.quantity = quantity;
    }

    @JsonProperty("color")
    public OptionProductVarchar getColor() {
        return color;
    }

    public void setColor(OptionProductVarchar color) {
        this.color = color;
    }

    @JsonProperty("size")
    public OptionProductVarchar getSize() {
        return size;
    }

    public void setSize(OptionProductVarchar size) {
        this.size = size;
    }

    @JsonProperty("image")
    public OptionProductVarchar getImage() {
        return image;
    }

    public void setImage(OptionProductVarchar image) {
        this.image = image;
    }
}
