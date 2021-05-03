package com.khangse616.serverfashionrs.mappers.impl;

import com.khangse616.serverfashionrs.mappers.RowMapper;
import com.khangse616.serverfashionrs.models.Product;
import com.khangse616.serverfashionrs.models.dto.ProductItemDTO;
import com.khangse616.serverfashionrs.services.IImageDataService;

public class ProductItemDTOMapper implements RowMapper<ProductItemDTO, Product> {

    @Override
    public ProductItemDTO mapRow(Product product) {
        return null;
    }

    @Override
    public ProductItemDTO mapRow(Product product, IImageDataService repository) {
        return null;
    }
}
