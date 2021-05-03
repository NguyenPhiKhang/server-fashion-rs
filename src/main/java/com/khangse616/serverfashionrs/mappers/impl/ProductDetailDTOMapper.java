package com.khangse616.serverfashionrs.mappers.impl;

import com.khangse616.serverfashionrs.mappers.RowMapper;
import com.khangse616.serverfashionrs.models.*;
import com.khangse616.serverfashionrs.models.dto.AttributeDTO;
import com.khangse616.serverfashionrs.models.dto.OptionProductDTO;
import com.khangse616.serverfashionrs.models.dto.ProductDetailDTO;
import com.khangse616.serverfashionrs.services.IImageDataService;
import java.util.ArrayList;
import java.util.List;

public class ProductDetailDTOMapper implements RowMapper<ProductDetailDTO, Product> {
    @Override
    public ProductDetailDTO mapRow(Product product) {
        return null;
    }

    @Override
    public ProductDetailDTO mapRow(Product product, IImageDataService imageDataService) {
        try {
            ProductDetailDTO productDetailDTO = new ProductDetailDTO();
            productDetailDTO.setId(product.getId());
            productDetailDTO.setName(product.getName());
            productDetailDTO.setFreeShip(product.isFreeShip());
            productDetailDTO.setPromotionPercent(product.getPromotionPercent());
            productDetailDTO.setPromotion(product.getPromotionPercent() > 0);
            productDetailDTO.setActive(product.isActive());
            productDetailDTO.setBrand(product.getBrand() != null ? product.getBrand().getName() : "khác");
            productDetailDTO.setDescription(product.getDescription());
            productDetailDTO.setShortDescription(product.getShortDescription());
            productDetailDTO.setHighlight(product.getHighlight());
            productDetailDTO.setMaterial(product.getMaterial());
            productDetailDTO.setPurpose(product.getPurpose());
            productDetailDTO.setMadeIn(product.getMadeIn());
            productDetailDTO.setSku(product.getSku());
            productDetailDTO.setVisibility(product.isVisibility());
            productDetailDTO.setSuitable_season(product.getSuitableSeason());
            productDetailDTO.setTypeId(product.getTypeId());

            List<AttributeDTO<OptionProductVarchar>> attributeDTOVarchar = new ArrayList<>();
            List<OptionProductDTO> optionProductDTOList = new ArrayList<>();
            List<String> listIdImage = new ArrayList<>();

            if (product.getProductLinks().size() > 0) {
                product.getProductLinks().forEach(pd -> {
                    OptionProductDTO optionProductDTO = new OptionProductDTO();
                    pd.getOptionProductVarchars().forEach(optionProductVarchar -> {
                        boolean isImage = optionProductDTO.getOptionProductVarcharList().stream().noneMatch(c->c.getAttribute().getCode().equals("image"));
                        createAttributeVarchar(optionProductVarchar, attributeDTOVarchar, listIdImage, optionProductDTO, isImage);
                    });
                    pd.getOptionProductIntegers().forEach(optionProductInteger -> {
                        createAttributeInteger(optionProductInteger, optionProductDTO);
                    });
                    pd.getOptionProductDecimals().forEach(optionProductDecimal -> {
                        createAttributeDecimal(optionProductDecimal, optionProductDTO);
                    });

                    optionProductDTOList.add(optionProductDTO);
                });
            } else {
                OptionProductDTO optionProductDTO = new OptionProductDTO();
                product.getOptionProductVarchars().forEach(optionProductVarchar -> {
                    createAttributeVarchar(optionProductVarchar, attributeDTOVarchar, listIdImage, null, false);
                });
                product.getOptionProductIntegers().forEach(optionProductInteger -> {
                    createAttributeInteger(optionProductInteger, optionProductDTO);
                });
                product.getOptionProductDecimals().forEach(optionProductDecimal -> {
                    createAttributeDecimal(optionProductDecimal, optionProductDTO);
                });
                optionProductDTOList.add(optionProductDTO);
            }

            List<ImageData> listImageData = imageDataService.findListImageDataByIds(listIdImage);
            attributeDTOVarchar.stream().filter(c -> c.getCode().equals("image")).findFirst().ifPresent(attributeImage -> attributeImage.getOptions().forEach(o -> {
                ImageData imgData = listImageData.stream().filter(i -> i.getId().equals(o.getValue())).findFirst().orElse(null);
                o.setValue(imgData != null ? "http:" + imgData.getLink().replace("fill_size", "700x817") : "");
            }));

            productDetailDTO.setOptionProductDTOList(optionProductDTOList);
            productDetailDTO.setListAttributeVarchar(attributeDTOVarchar);

            productDetailDTO.setCategory(product.getCategory());

            Category category = product.getCategory();
            StringBuilder categoriesStr = new StringBuilder();

            for(int i = product.getCategory().getLevel();i>=0;i--){
                categoriesStr.insert(0, "/"+category.getId());
                category = category.getParentCategory();
            }
            categoriesStr.insert(0, 0);

            productDetailDTO.setCategories(categoriesStr.toString());

            return productDetailDTO;
        } catch (
                Exception ex) {
            return null;
        }
    }

    private void createAttributeVarchar(OptionProductVarchar optionProductVarchar, List<AttributeDTO<OptionProductVarchar>> attributeDTOVarchar
            , List<String> listIdImage, OptionProductDTO optionProductDTO, boolean isImage) {
        Attribute attr = optionProductVarchar.getAttribute();
        AttributeDTO<OptionProductVarchar> attributeDTOStream = attributeDTOVarchar.stream().filter(c -> c.getId() == attr.getId()).findFirst().orElse(null);
        if (attributeDTOStream == null) {
            AttributeDTO<OptionProductVarchar> attr1 = new AttributeDTO<OptionProductVarchar>(attr.getId(), attr.getType(), attr.getLabel(), attr.getCode());
            List<OptionProductVarchar> optionProductVarchars = new ArrayList<>();
            optionProductVarchars.add(optionProductVarchar);
            if (attr.getCode().equals("image")) {
                listIdImage.add(optionProductVarchar.getValue());
            }
            attr1.setOptions(optionProductVarchars);

            attributeDTOVarchar.add(attr1);
        } else {
            List<OptionProductVarchar> optionProductVarchars = attributeDTOStream.getOptions();
            if (optionProductVarchars.stream().noneMatch(i -> i.getId() == optionProductVarchar.getId())) {
                optionProductVarchars.add(optionProductVarchar);
                if (attr.getCode().equals("image")) {
                    listIdImage.add(optionProductVarchar.getValue());
                }

                attributeDTOStream.setOptions(optionProductVarchars);
            }
        }
        if (optionProductDTO != null && (!attr.getCode().equals("image")|| (attr.getCode().equals("image")&&isImage))) {
            List<OptionProductVarchar> list = optionProductDTO.getOptionProductVarcharList();
            list.add(optionProductVarchar);
            optionProductDTO.setOptionProductVarcharList(list);
        }
    }

    private void createAttributeInteger(OptionProductInteger optionProductInteger
            , OptionProductDTO optionProductDTO) {
        optionProductDTO.setQuantity(optionProductInteger);
    }

    private void createAttributeDecimal(OptionProductDecimal optionProductDecimal, OptionProductDTO optionProductDTO) {
        optionProductDTO.setPrice(optionProductDecimal);
    }
}
