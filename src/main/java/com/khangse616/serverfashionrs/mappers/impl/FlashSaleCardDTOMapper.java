package com.khangse616.serverfashionrs.mappers.impl;

import com.khangse616.serverfashionrs.mappers.RowMapper;
import com.khangse616.serverfashionrs.models.FlashSaleProduct;
import com.khangse616.serverfashionrs.models.dto.FlashSaleCardDTO;
import com.khangse616.serverfashionrs.models.dto.SearchProductDTO;
import com.khangse616.serverfashionrs.services.IImageDataService;

public class FlashSaleCardDTOMapper implements RowMapper<FlashSaleCardDTO, FlashSaleProduct> {
    @Override
    public FlashSaleCardDTO mapRow(FlashSaleProduct flashSaleProduct) {
        return null;
    }

    @Override
    public FlashSaleCardDTO mapRow(FlashSaleProduct flashSaleProduct, IImageDataService repository) {
        try {
            FlashSaleCardDTO flashSaleCardDTO = new FlashSaleCardDTO();
            flashSaleCardDTO.setId(flashSaleProduct.getId());
            flashSaleCardDTO.setQuantity(flashSaleProduct.getQuantity());
            flashSaleCardDTO.setSaleAmount(flashSaleProduct.getSaleAmount());
            flashSaleCardDTO.setPercentDiscount(flashSaleProduct.getPercentDiscount());
            flashSaleCardDTO.setProductItemDTO(new ProductItemDTOMapper().mapRow(flashSaleProduct.getProduct(), repository));

            return flashSaleCardDTO;
        } catch (Exception ex) {
            return null;
        }
    }
}
