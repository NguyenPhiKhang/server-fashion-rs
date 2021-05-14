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

public class CartItemDTOMapper implements RowMapper<CartItemDTO, Cart> {
    @Override
    public CartItemDTO mapRow(Cart cart) {
        return null;
    }

    @Override
    public CartItemDTO mapRow(Cart cart, IImageDataService repository) {
        try {
            Product product = cart.getProduct();

            CartItemDTO cartItemDTO = new CartItemDTO(cart.getId(), product.getId(), product.getName(), cart.getAmount());
            OptionProductItemCartDTO optionProductItemCartDTO = new OptionProductItemCartDTO();

            if (product.getTypeId().equals("configurable")) {
                Product productOption = cart.getProductOption();
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
                                ImageData imageData = repository.findImageById(optionProductVarchar.getValue());

                                optionProductVarchar.setValue("https:" + imageData.getLink().replace("fill_size", "700x817"));
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

//    private void createAttributeVarchar(OptionProductVarchar optionProductVarchar, OptionProductDTO optionProductDTO) {
//        Attribute attr = optionProductVarchar.getAttribute();
//        AttributeDTO<OptionProductVarchar> attributeDTOStream = attributeDTOVarchar.stream().filter(c -> c.getId() == attr.getId()).findFirst().orElse(null);
//        if (attributeDTOStream == null) {
//            AttributeDTO<OptionProductVarchar> attr1 = new AttributeDTO<OptionProductVarchar>(attr.getId(), attr.getType(), attr.getLabel(), attr.getCode());
//            List<OptionProductVarchar> optionProductVarchars = new ArrayList<>();
//            optionProductVarchars.add(optionProductVarchar);
//            if (attr.getCode().equals("image")) {
//                listIdImage.add(optionProductVarchar.getValue());
//            }
//            attr1.setOptions(optionProductVarchars);
//
//            attributeDTOVarchar.add(attr1);
//        } else {
//            List<OptionProductVarchar> optionProductVarchars = attributeDTOStream.getOptions();
//            if (optionProductVarchars.stream().noneMatch(i -> i.getId() == optionProductVarchar.getId())) {
//                optionProductVarchars.add(optionProductVarchar);
//                if (attr.getCode().equals("image")) {
//                    listIdImage.add(optionProductVarchar.getValue());
//                }
//
//                attributeDTOStream.setOptions(optionProductVarchars);
//            }
//        }
//
//        if (optionProductDTO != null && (!attr.getCode().equals("image") || (attr.getCode().equals("image") && isImage))) {
//            List<OptionProductVarchar> list = optionProductDTO.getOptionProductVarcharList();
//            list.add(optionProductVarchar);
//            optionProductDTO.setOptionProductVarcharList(list);
//        }
//    }
}
