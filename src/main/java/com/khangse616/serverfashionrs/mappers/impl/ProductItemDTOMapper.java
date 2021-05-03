package com.khangse616.serverfashionrs.mappers.impl;

import com.khangse616.serverfashionrs.mappers.RowMapper;
import com.khangse616.serverfashionrs.models.ImageData;
import com.khangse616.serverfashionrs.models.OptionProductDecimal;
import com.khangse616.serverfashionrs.models.OptionProductVarchar;
import com.khangse616.serverfashionrs.models.Product;
import com.khangse616.serverfashionrs.models.dto.OptionProductDTO;
import com.khangse616.serverfashionrs.models.dto.PriceResultDTO;
import com.khangse616.serverfashionrs.models.dto.ProductItemDTO;
import com.khangse616.serverfashionrs.services.IImageDataService;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.List;

public class ProductItemDTOMapper implements RowMapper<ProductItemDTO, Product> {

    @Override
    public ProductItemDTO mapRow(Product product) {
        return null;
    }

    @Override
    public ProductItemDTO mapRow(Product product, IImageDataService imageDataService) {
        try {
            ProductItemDTO productItemDTO = new ProductItemDTO();
            productItemDTO.setId(product.getId());
            productItemDTO.setName(product.getName());
            productItemDTO.setCategory(product.getCategory());
            productItemDTO.setFreeShip(product.isFreeShip());
            productItemDTO.setPromotionPercent(product.getPromotionPercent());
            productItemDTO.setOrderCount(product.getOrderCount());
//            productItemDTO.setImgUrl(ImageUtil.addressImage(product.getImgUrl().getId()));

//            productItemDTO.setCountRating(product.getRatings().size());
//            RatingStar ratingStar = product.getRatingStar();
//            int totalStar = ratingStar.getStar1() + ratingStar.getStar2() + ratingStar.getStar3() + ratingStar.getStar4() + ratingStar.getStar5();
//            float percentStar = totalStar > 0 ? (float) (ratingStar.getStar1() + ratingStar.getStar2() * 2 + ratingStar.getStar3() * 3 + ratingStar.getStar4() * 4 + ratingStar.getStar5() * 5)
//                    / totalStar : 0;
            DecimalFormat decimalFormat = new DecimalFormat("#.##");
            productItemDTO.setPercentStar(Float.parseFloat(decimalFormat.format(4.32)));
            productItemDTO.setCountRating(20);

            OptionProductVarchar optionProductImg = null;
            BigDecimal priceMax = new BigDecimal(0);
            BigDecimal priceMin = new BigDecimal(0);

            if (product.getProductLinks().size() > 0) {
                for (Product pd : product.getProductLinks()) {
                    for (OptionProductDecimal optionProductDecimal : pd.getOptionProductDecimals()) {
                        if (priceMin.equals(new BigDecimal(0)))
                            priceMin = optionProductDecimal.getValue();

                        if (optionProductDecimal.getValue().compareTo(priceMax) == 1)
                            priceMax = optionProductDecimal.getValue();

                        if (optionProductDecimal.getValue().compareTo(priceMax) == -1)
                            priceMin = optionProductDecimal.getValue();
                    }
                }

                Product pd = product.getProductLinks().iterator().next();
                for (OptionProductVarchar optionProductVarchar : pd.getOptionProductVarchars()) {
                    if (optionProductVarchar.getAttribute().getCode().equals("image")) {
                        optionProductImg = optionProductVarchar;
                        break;
                    }
                }
            } else {
                for (OptionProductDecimal optionProductDecimal : product.getOptionProductDecimals()) {
                    if (priceMin.equals(new BigDecimal(0)))
                        priceMin = optionProductDecimal.getValue();

                    if (optionProductDecimal.getValue().compareTo(priceMax) == 1)
                        priceMax = optionProductDecimal.getValue();

                    if (optionProductDecimal.getValue().compareTo(priceMax) == -1)
                        priceMin = optionProductDecimal.getValue();
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

            PriceResultDTO price = new PriceResultDTO(priceMax, priceMin);

            productItemDTO.setPrice(price);

            assert optionProductImg != null;
            ImageData imageData = imageDataService.findImageById(optionProductImg.getValue());
            productItemDTO.setImgUrl(imageData != null ? "http:" + imageData.getLink().replace("fill_size", "255x298") : "");

            return productItemDTO;
        } catch (Exception ex) {
            return null;
        }
    }
}