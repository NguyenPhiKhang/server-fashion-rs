package com.khangse616.serverfashionrs.models.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CartItemDTO {
    private int cartId;
    private int productId;
    private String nameProduct;
    private int amount;
    private OptionProductItemCartDTO optionProduct;

    public CartItemDTO() {
    }

    public CartItemDTO(int cartId, int productId, String nameProduct, int amount) {
        this.cartId = cartId;
        this.productId = productId;
        this.nameProduct = nameProduct;
        this.amount = amount;
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

    @JsonProperty("amount")
    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @JsonProperty("option-product")
    public OptionProductItemCartDTO getOptionProduct() {
        return optionProduct;
    }

    public void setOptionProduct(OptionProductItemCartDTO optionProduct) {
        this.optionProduct = optionProduct;
    }
}
