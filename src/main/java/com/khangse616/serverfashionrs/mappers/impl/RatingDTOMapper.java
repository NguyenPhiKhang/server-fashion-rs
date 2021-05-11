package com.khangse616.serverfashionrs.mappers.impl;

import com.khangse616.serverfashionrs.mappers.RowMapper;
import com.khangse616.serverfashionrs.models.Rating;
import com.khangse616.serverfashionrs.models.User;
import com.khangse616.serverfashionrs.models.dto.RatingDTO;
import com.khangse616.serverfashionrs.services.IImageDataService;

public class RatingDTOMapper implements RowMapper<RatingDTO, Rating> {
    @Override
    public RatingDTO mapRow(Rating rating) {
        try{
            RatingDTO ratingDTO = new RatingDTO();
            ratingDTO.setId(rating.getId());
            ratingDTO.setComment(rating.getComment());
            User user = rating.getUser();
            ratingDTO.setCustomerId(user.getId());
//            ratingDTO.setImage( ImageUtil.addressImage(user.getImageAvatar().getId()));
            ratingDTO.setStar(rating.getStar());
            ratingDTO.setImageAvatar(user.getImageAvatar().getLink());
            ratingDTO.setTimeUpdated(rating.getTimeUpdated());
            ratingDTO.setUserName(user.getName());

            return ratingDTO;
        }catch (Exception ex){
            return null;
        }
    }

    @Override
    public RatingDTO mapRow(Rating rating, IImageDataService repository) {
        return null;
    }
}
