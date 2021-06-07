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

            assert optionProductImg != null;
            ImageData imageData = repository.findImageById(optionProductImg.getValue());
            hotSearchDTO.setLinkImage(imageData != null ? "http:" + imageData.getLink().replace("fill_size", "255x298") : "");
            hotSearchDTO.setKeyword(productStringHashMap.getValue());
            return hotSearchDTO;
        } catch (Exception ex) {
            return null;
        }
    }
}
