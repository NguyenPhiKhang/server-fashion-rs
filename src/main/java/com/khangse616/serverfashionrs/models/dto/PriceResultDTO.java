package com.khangse616.serverfashionrs.models.dto;

import java.math.BigDecimal;

public class PriceResultDTO {
    private BigDecimal priceMax;
    private BigDecimal priceMin;

    public PriceResultDTO() {
    }

    public PriceResultDTO(BigDecimal priceMax, BigDecimal priceMin) {
        this.priceMax = priceMax;
        this.priceMin = priceMin;
    }

    public BigDecimal getPriceMax() {
        return priceMax;
    }

    public void setPriceMax(BigDecimal priceMax) {
        this.priceMax = priceMax;
    }

    public BigDecimal getPriceMin() {
        return priceMin;
    }

    public void setPriceMin(BigDecimal priceMin) {
        this.priceMin = priceMin;
    }
}
