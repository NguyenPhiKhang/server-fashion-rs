package com.khangse616.serverfashionrs.models.dto;

public class FlashSaleCardDTO {
    private int id;
    private ProductItemDTO productItemDTO;
    private int percentDiscount;
    private int quantity;
    private int saleAmount;

    public FlashSaleCardDTO() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ProductItemDTO getProductItemDTO() {
        return productItemDTO;
    }

    public void setProductItemDTO(ProductItemDTO productItemDTO) {
        this.productItemDTO = productItemDTO;
    }

    public int getPercentDiscount() {
        return percentDiscount;
    }

    public void setPercentDiscount(int percentDiscount) {
        this.percentDiscount = percentDiscount;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getSaleAmount() {
        return saleAmount;
    }

    public void setSaleAmount(int saleAmount) {
        this.saleAmount = saleAmount;
    }
}
