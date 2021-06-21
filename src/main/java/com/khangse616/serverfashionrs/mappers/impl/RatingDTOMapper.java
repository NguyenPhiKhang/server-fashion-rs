package com.khangse616.serverfashionrs.mappers.impl;

import com.khangse616.serverfashionrs.Utils.EnvUtil;
import com.khangse616.serverfashionrs.Utils.StringUtil;
import com.khangse616.serverfashionrs.mappers.RowMapper;
import com.khangse616.serverfashionrs.models.ImageData;
import com.khangse616.serverfashionrs.models.Rating;
import com.khangse616.serverfashionrs.models.User;
import com.khangse616.serverfashionrs.models.dto.RatingDTO;
import com.khangse616.serverfashionrs.services.IImageDataService;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Timestamp;
import java.util.stream.Collectors;

public class RatingDTOMapper implements RowMapper<RatingDTO, Rating> {

    @Override
    public RatingDTO mapRow(Rating rating) {
        try {
            RatingDTO ratingDTO = new RatingDTO();
            ratingDTO.setId(rating.getId());
            ratingDTO.setComment(rating.getComment());
            User user = rating.getUser();
            ratingDTO.setUserId(user.getId());
//            ratingDTO.setImage( ImageUtil.addressImage(user.getImageAvatar().getId()));
            ratingDTO.setStar(rating.getStar());
            ratingDTO.setImageAvatar(user.getImageAvatar() == null ? "https://media3.scdn.vn/images/apps/icon_user_default.png" : user.getImageAvatar().getLink());
            ratingDTO.setTimeUpdated(StringUtil.convertTimestampToString(rating.getTimeUpdated()));
            ratingDTO.setUserName(user.getName());

//            ratingDTO.setImageRating(rating.getDataImages().stream().map(v->"http:"+ v.getLink().replace("fill_size", "255x298")).collect(Collectors.toList()));

            System.out.println(EnvUtil.getInstance().getServerUrlPrefix());

            rating.getProductAttribute().getOptionProductVarchars().forEach(v -> {
                if (v.getAttribute().getId() == 80) {
                    ratingDTO.setColor(v.getValue());
                } else {
                    if (v.getAttribute().getId() == 164) {
                        ratingDTO.setSize(v.getValue());
                    }
                }
            });

            return ratingDTO;
        } catch (Exception ex) {
            return null;
        }
    }

    @Override
    public RatingDTO mapRow(Rating rating, IImageDataService repository) {
        return null;
    }
}
