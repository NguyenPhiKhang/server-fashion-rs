package com.khangse616.serverfashionrs.models.dto;

public class SearchProductDTO {
    private ProductItemDTO productItemDTO;
    private double cosine;

    public SearchProductDTO() {
    }

    public SearchProductDTO(ProductItemDTO productItemDTO, double cosine) {
        this.productItemDTO = productItemDTO;
        this.cosine = cosine;
    }

    public ProductItemDTO getProductItemDTO() {
        return productItemDTO;
    }

    public void setProductItemDTO(ProductItemDTO productItemDTO) {
        this.productItemDTO = productItemDTO;
    }

    public double getCosine() {
        return cosine;
    }

    public void setCosine(double cosine) {
        this.cosine = cosine;
    }
}
