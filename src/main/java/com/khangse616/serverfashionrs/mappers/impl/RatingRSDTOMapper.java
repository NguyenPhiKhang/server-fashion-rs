package com.khangse616.serverfashionrs.mappers.impl;

import com.khangse616.serverfashionrs.mappers.RowMapper;
import com.khangse616.serverfashionrs.models.Rating;
import com.khangse616.serverfashionrs.models.dto.RecommendSystem.RatingRSDTO;
import com.khangse616.serverfashionrs.services.IImageDataService;

public class RatingRSDTOMapper implements RowMapper<RatingRSDTO, Rating> {
    @Override
    public RatingRSDTO mapRow(Rating rating) {
        try{
            return new RatingRSDTO(rating.getUser().getId(), rating.getProduct().getId(), rating.getStar());
        }catch (Exception ex){
            return null;
        }
    }

    @Override
    public RatingRSDTO mapRow(Rating rating, IImageDataService repository) {
        return null;
    }
}
