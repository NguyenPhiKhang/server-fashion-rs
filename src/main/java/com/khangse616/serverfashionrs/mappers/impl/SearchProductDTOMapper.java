package com.khangse616.serverfashionrs.mappers.impl;

import com.khangse616.serverfashionrs.mappers.RowMapper;
import com.khangse616.serverfashionrs.models.*;
import com.khangse616.serverfashionrs.models.dto.PriceResultDTO;
import com.khangse616.serverfashionrs.models.dto.ProductItemDTO;
import com.khangse616.serverfashionrs.models.dto.SearchProductDTO;
import com.khangse616.serverfashionrs.services.IImageDataService;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

public class SearchProductDTOMapper implements RowMapper<SearchProductDTO, Map.Entry<Product, Double>> {
    @Override
    public SearchProductDTO mapRow(Map.Entry<Product, Double> productDoubleEntry) {
        return null;
    }

    @Override
    public SearchProductDTO mapRow(Map.Entry<Product, Double> productDoubleEntry, IImageDataService repository) {
        try {
            return new SearchProductDTO(new ProductItemDTOMapper().mapRow(productDoubleEntry.getKey(), repository), productDoubleEntry.getValue());
        } catch (Exception ex) {
            return null;
        }
    }
}
