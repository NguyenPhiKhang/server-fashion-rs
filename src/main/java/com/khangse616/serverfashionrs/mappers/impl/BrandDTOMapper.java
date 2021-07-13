package com.khangse616.serverfashionrs.mappers.impl;

import com.khangse616.serverfashionrs.mappers.RowMapper;
import com.khangse616.serverfashionrs.models.Brand;
import com.khangse616.serverfashionrs.models.dto.BrandDTO;
import com.khangse616.serverfashionrs.services.IImageDataService;

public class BrandDTOMapper implements RowMapper<BrandDTO, Brand> {
    @Override
    public BrandDTO mapRow(Brand brand) {
        try{
            return new BrandDTO(brand.getId(), brand.getName(), brand.getIcon(), brand.getProducts().size());
        }catch (Exception ex){
            return null;
        }
    }

    @Override
    public BrandDTO mapRow(Brand brand, IImageDataService repository) {
        return null;
    }
}
