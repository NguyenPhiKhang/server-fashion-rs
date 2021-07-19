package com.khangse616.serverfashionrs.mappers.impl;

import com.khangse616.serverfashionrs.mappers.RowMapper;
import com.khangse616.serverfashionrs.models.*;
import com.khangse616.serverfashionrs.models.dto.HotSearchDTO;
import com.khangse616.serverfashionrs.models.dto.PriceResultDTO;
import com.khangse616.serverfashionrs.models.dto.ProductDetailDTO;
import com.khangse616.serverfashionrs.models.dto.ProductItemDTO;
import com.khangse616.serverfashionrs.services.IImageDataService;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

public class HotSearchDTOMapper implements RowMapper<HotSearchDTO, Map.Entry<Product, String>> {

    @Override
    public HotSearchDTO mapRow(Map.Entry<Product, String> productStringHashMap) {
        return null;
    }

    @Override
    public HotSearchDTO mapRow(Map.Entry<Product, String> productStringHashMap, IImageDataService repository) {
        try {

            HotSearchDTO hotSearchDTO = new HotSearchDTO();

            Product product = productStringHashMap.getKey();

            OptionProductVarchar optionProductImg = null;

            if (product.getProductLinks().size() > 0) {
                Product pd = product.getProductLinks().iterator().next();
                for (OptionProductVarchar optionProductVarchar : pd.getOptionProductVarchars()) {
                    if (optionProductVarchar.getAttribute().getCode().equals("image")) {
                        optionProductImg = optionProductVarchar;
                        break;
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

//            assert optionProductImg != null;
//            ImageData imageData = repository.findImageById(optionProductImg.getValue());
//            hotSearchDTO.setLinkImage(imageData != null ? "http:" + imageData.getLink().replace("fill_size", "255x298") : "");

            if (optionProductImg != null) {
                if (optionProductImg.getValue() == null || optionProductImg.getValue().isEmpty())
                    hotSearchDTO.setLinkImage("https://developers.google.com/maps/documentation/streetview/images/error-image-generic.png?hl=vi");
                else if (optionProductImg.getValue().contains("http")) {
                    hotSearchDTO.setLinkImage(optionProductImg.getValue());
                } else {
                    ImageData imageData = repository.findImageById(optionProductImg.getValue());

                    if (imageData != null) {
                        if (imageData.getData() != null)
                            hotSearchDTO.setLinkImage("http://localhost:8080/api/v1/image/" + imageData.getId());
                        else if (imageData.getLink() == null || imageData.getLink().isEmpty()) {
                            hotSearchDTO.setLinkImage("https://developers.google.com/maps/documentation/streetview/images/error-image-generic.png?hl=vi");
                        } else hotSearchDTO.setLinkImage("http:" + imageData.getLink().replace("fill_size", "255x298"));
                    } else {
                        hotSearchDTO.setLinkImage("https://developers.google.com/maps/documentation/streetview/images/error-image-generic.png?hl=vi");
                    }
                }
            } else
                hotSearchDTO.setLinkImage("https://developers.google.com/maps/documentation/streetview/images/error-image-generic.png?hl=vi");

            hotSearchDTO.setKeyword(productStringHashMap.getValue());
            return hotSearchDTO;
        } catch (Exception ex) {
            return null;
        }
    }
}
