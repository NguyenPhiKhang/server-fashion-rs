package com.khangse616.serverfashionrs.mappers.impl;

import com.khangse616.serverfashionrs.mappers.RowMapper;
import com.khangse616.serverfashionrs.models.*;
import com.khangse616.serverfashionrs.models.dto.AttributeDTO;
import com.khangse616.serverfashionrs.models.dto.CartItemDTO;
import com.khangse616.serverfashionrs.models.dto.OptionProductDTO;
import com.khangse616.serverfashionrs.models.dto.OptionProductItemCartDTO;
import com.khangse616.serverfashionrs.models.dto.RecommendSystem.RatingRSDTO;
import com.khangse616.serverfashionrs.services.IImageDataService;

import java.util.ArrayList;
import java.util.List;

public class CartItemDTOMapper implements RowMapper<CartItemDTO, CartItem> {
    @Override
    public CartItemDTO mapRow(CartItem cartItem) {
        return null;
    }

    @Override
    public CartItemDTO mapRow(CartItem cartItem, IImageDataService repository) {
        try {
            Product product = cartItem.getProduct();

            CartItemDTO cartItemDTO = new CartItemDTO(cartItem.getId(), cartItem.getCart().getId(), product.getId(), product.getName(), cartItem.getQuantity(), product.getPromotionPercent());
            OptionProductItemCartDTO optionProductItemCartDTO = new OptionProductItemCartDTO();

            if (product.getTypeId().equals("configurable")) {
                Product productOption = cartItem.getProductOption();
                optionProductItemCartDTO.setProductOptionId(productOption.getId());
                boolean isImage = false;
                for (OptionProductVarchar optionProductVarchar : productOption.getOptionProductVarchars()) {
                    Attribute attribute = optionProductVarchar.getAttribute();
                    if (attribute.getId() == 80) {
                        optionProductItemCartDTO.setColor(optionProductVarchar);
                    } else {
                        if (attribute.getId() == 164)
                            optionProductItemCartDTO.setSize(optionProductVarchar);
                        else {
                            if (attribute.getId() == 240799 && optionProductItemCartDTO.getImage() == null) {
                                isImage = true;
                                if (optionProductVarchar.getValue().contains("https"))
                                    optionProductVarchar.setValue(optionProductVarchar.getValue());
                                else {
                                    ImageData imageData = repository.findImageById(optionProductVarchar.getValue());
                                    optionProductVarchar.setValue("https:" + imageData.getLink().replace("fill_size", "700x817"));
                                }
                                optionProductItemCartDTO.setImage(optionProductVarchar);
                            }
                        }
                    }
                }

                if (!isImage) {
                    OptionProductVarchar ov = product.getOptionProductVarchars().stream().findFirst().orElse(null);
                    assert ov != null;
                    ImageData imageData = repository.findImageById(ov.getValue());

                    ov.setValue("https:" + imageData.getLink().replace("fill_size", "700x817"));
                    optionProductItemCartDTO.setImage(ov);
                }
                productOption.getOptionProductIntegers().forEach(optionProductItemCartDTO::setQuantity);
                productOption.getOptionProductDecimals().forEach(optionProductItemCartDTO::setPrice);

            } else {
                optionProductItemCartDTO.setProductOptionId(0);
                product.getOptionProductIntegers().forEach(optionProductItemCartDTO::setQuantity);
                product.getOptionProductDecimals().forEach(optionProductItemCartDTO::setPrice);
                OptionProductVarchar ov = product.getOptionProductVarchars().stream().findFirst().orElse(null);
                assert ov != null;
                ImageData imageData = repository.findImageById(ov.getValue());

                ov.setValue("https:" + imageData.getLink().replace("fill_size", "700x817"));
                optionProductItemCartDTO.setImage(ov);
            }

            cartItemDTO.setOptionProduct(optionProductItemCartDTO);

            return cartItemDTO;
        } catch (Exception ex) {
            return null;
        }
    }
}
