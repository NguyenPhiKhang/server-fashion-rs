package com.khangse616.serverfashionrs.mappers.impl;

import com.khangse616.serverfashionrs.Utils.StringUtil;
import com.khangse616.serverfashionrs.mappers.RowMapper;
import com.khangse616.serverfashionrs.models.*;
import com.khangse616.serverfashionrs.models.dto.CardMyRatingDTO;
import com.khangse616.serverfashionrs.services.IImageDataService;

import java.util.stream.Collectors;

public class CardMyRatingDTOMapper implements RowMapper<CardMyRatingDTO, Rating> {
    @Override
    public CardMyRatingDTO mapRow(Rating rating) {
        return null;
    }

    @Override
    public CardMyRatingDTO mapRow(Rating rating, IImageDataService repository) {
        try {
            CardMyRatingDTO cardMyRatingDTO = new CardMyRatingDTO();
            cardMyRatingDTO.setId(rating.getId());
            User user = rating.getUser();
            cardMyRatingDTO.setUserId(user.getId());
            cardMyRatingDTO.setUserName(user.getName());
            String imageAvatar = "";
            if (user.getImageAvatar() == null)
                imageAvatar = "https://media3.scdn.vn/images/apps/icon_user_default.png";
            else if (user.getImageAvatar().getData() != null)
                imageAvatar = "http://localhost:8080/api/v1/image/" + user.getImageAvatar().getId();
            else imageAvatar = user.getImageAvatar().getLink();
            cardMyRatingDTO.setImageAvatar(imageAvatar);

            cardMyRatingDTO.setStar(rating.getStar());
            cardMyRatingDTO.setComment(rating.getComment());
            cardMyRatingDTO.setTimeUpdated(StringUtil.convertTimestampToString(rating.getTimeUpdated()));

            cardMyRatingDTO.setFileRating(rating.getDataImages().stream().map(v -> new FileRatingDTOMapper().mapRow(v)).collect(Collectors.toList()));

            Product product = rating.getProduct();
            cardMyRatingDTO.setNameProduct(product.getName());
            cardMyRatingDTO.setIdProduct(product.getId());

            boolean isSize = false;
            boolean isColor = false;

            OptionProductVarchar optionProductImg = null;

            if (product.getTypeId().equals("configurable")) {
                Product productOption = rating.getProductAttribute();
                cardMyRatingDTO.setIdProductOption(productOption.getId());

                for (OptionProductVarchar optionProductVarchar : productOption.getOptionProductVarchars()) {
                    Attribute attribute = optionProductVarchar.getAttribute();

                    if (optionProductImg != null && isSize && isColor)
                        break;
                    if (attribute.getId() == 80) {
                        cardMyRatingDTO.setColor(optionProductVarchar.getValue());
                        isColor = true;
                    } else {
                        if (attribute.getId() == 164) {
                            cardMyRatingDTO.setSize(optionProductVarchar.getValue());
                            isSize = true;
                        } else {
                            cardMyRatingDTO.setImageProduct(optionProductVarchar.getValue());
                            optionProductImg = optionProductVarchar;
                        }
                    }
                }
            }

            if (optionProductImg == null) {
                for (OptionProductVarchar optionProductVarchar : product.getOptionProductVarchars()) {
                    if (optionProductVarchar.getAttribute().getCode().equals("image")) {
                        optionProductImg = optionProductVarchar;
                        break;
                    }
                }
            }

            if (optionProductImg != null) {
                if (optionProductImg.getValue() == null)
                    cardMyRatingDTO.setImageProduct("https://developers.google.com/maps/documentation/streetview/images/error-image-generic.png?hl=vi");
                else if (optionProductImg.getValue().contains("http")) {
                    cardMyRatingDTO.setImageProduct(optionProductImg.getValue());
                } else {
                    ImageData imageData = repository.findImageById(optionProductImg.getValue());

                    if (imageData != null) {
                        if (imageData.getData() != null)
                            cardMyRatingDTO.setImageProduct("http://localhost:8080/api/v1/image/" + imageData.getId());
                        else if (imageData.getLink() == null || imageData.getLink().isEmpty()) {
                            cardMyRatingDTO.setImageProduct("https://developers.google.com/maps/documentation/streetview/images/error-image-generic.png?hl=vi");
                        } else
                            cardMyRatingDTO.setImageProduct("http:" + imageData.getLink().replace("fill_size", "255x298"));
                    } else {
                        cardMyRatingDTO.setImageProduct("https://developers.google.com/maps/documentation/streetview/images/error-image-generic.png?hl=vi");
                    }
                }
            } else
                cardMyRatingDTO.setImageProduct("https://developers.google.com/maps/documentation/streetview/images/error-image-generic.png?hl=vi");


            return cardMyRatingDTO;
        } catch (Exception ex) {
            return null;
        }
    }
}
