package com.khangse616.serverfashionrs.models.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CartItemDTO {
    private int cartId;
    private int productId;
    private String nameProduct;
    private int quantity;
    private float discount;
    private OptionProductItemCartDTO optionProduct;

    public CartItemDTO() {
    }

    public CartItemDTO(int cartId, int productId, String nameProduct, int quantity, float discount) {
        this.cartId = cartId;
        this.productId = productId;
        this.nameProduct = nameProduct;
        this.quantity = quantity;
        this.discount = discount;
    }

    @JsonProperty("cart-id")
    public int getCartId() {
        return cartId;
    }

    public void setCartId(int cartId) {
        this.cartId = cartId;
    }

    @JsonProperty("product-id")
    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    @JsonProperty("name-product")
    public String getNameProduct() {
        return nameProduct;
    }

    public void setNameProduct(String nameProduct) {
        this.nameProduct = nameProduct;
    }

    @JsonProperty("quantity")
    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @JsonProperty("discount")
    public float getDiscount() {
        return discount;
    }

    public void setDiscount(float discount) {
        this.discount = discount;
    }

    @JsonProperty("option-product")
    public OptionProductItemCartDTO getOptionProduct() {
        return optionProduct;
    }

    public void setOptionProduct(OptionProductItemCartDTO optionProduct) {
        this.optionProduct = optionProduct;
    }
}
