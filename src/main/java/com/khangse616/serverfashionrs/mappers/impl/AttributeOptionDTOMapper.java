package com.khangse616.serverfashionrs.mappers.impl;

import com.khangse616.serverfashionrs.mappers.RowMapper;
import com.khangse616.serverfashionrs.models.*;
import com.khangse616.serverfashionrs.models.dto.AttributeDTO;
import com.khangse616.serverfashionrs.models.dto.AttributeOptionDTO;
import com.khangse616.serverfashionrs.models.dto.OptionProductDTO;
import com.khangse616.serverfashionrs.models.dto.OptionProductVarcharDTO;
import com.khangse616.serverfashionrs.services.IImageDataService;

import java.util.ArrayList;
import java.util.List;

public class AttributeOptionDTOMapper implements RowMapper<AttributeOptionDTO, Product> {
    @Override
    public AttributeOptionDTO mapRow(Product product) {
        return null;
    }

    @Override
    public AttributeOptionDTO mapRow(Product product, IImageDataService repository) {
        try {
            AttributeOptionDTO attributeOptionDTO = new AttributeOptionDTO();

            List<AttributeDTO<OptionProductVarchar>> attributeDTOVarchar = new ArrayList<>();
            List<OptionProductDTO> optionProductDTOList = new ArrayList<>();
            List<String> listIdImage = new ArrayList<>();

            int quantity = 0;

            if (product.getProductLinks().size() > 0) {
                for (Product pd : product.getProductLinks()) {
                    OptionProductDTO optionProductDTO = new OptionProductDTO();
                    optionProductDTO.setProductAttributeId(pd.getId());
                    for (OptionProductVarchar optionProductVarchar : pd.getOptionProductVarchars()) {
                        boolean isImage = optionProductDTO.getOptionProductVarcharList().stream().noneMatch(c -> c.getAttributeId() == 240799);
                        createAttributeVarchar(optionProductVarchar, attributeDTOVarchar, listIdImage, optionProductDTO, isImage, repository);
                    }
                    for (OptionProductInteger optionProductInteger : pd.getOptionProductIntegers()) {
                        quantity += optionProductInteger.getValue();
                        createAttributeInteger(optionProductInteger, optionProductDTO);
                    }
                    pd.getOptionProductDecimals().forEach(optionProductDecimal -> {
                        createAttributeDecimal(optionProductDecimal, optionProductDTO);
                    });

                    optionProductDTOList.add(optionProductDTO);
                }
            } else {
                OptionProductDTO optionProductDTO = new OptionProductDTO();
                product.getOptionProductVarchars().forEach(optionProductVarchar -> {
                    createAttributeVarchar(optionProductVarchar, attributeDTOVarchar, listIdImage, null, false, repository);
                });
                for (OptionProductInteger optionProductInteger : product.getOptionProductIntegers()) {
                    quantity += optionProductInteger.getValue();
                    createAttributeInteger(optionProductInteger, optionProductDTO);
                }
                product.getOptionProductDecimals().forEach(optionProductDecimal -> {
                    createAttributeDecimal(optionProductDecimal, optionProductDTO);
                });
                optionProductDTOList.add(optionProductDTO);
            }

            boolean checkHaveImages = attributeDTOVarchar.stream().anyMatch(img -> img.getCode().equals("image"));
            if (!checkHaveImages) {
                product.getOptionProductVarchars().forEach(optionProductVarchar -> {
                    createAttributeVarchar(optionProductVarchar, attributeDTOVarchar, listIdImage, null, false, repository);
                });
            }

            List<ImageData> listImageData = repository.findListImageDataByIds(listIdImage);
            attributeDTOVarchar.stream().filter(c -> c.getCode().equals("image")).findFirst().ifPresent(attributeImage -> attributeImage.getOptions().forEach(o -> {
                ImageData imgData = listImageData.stream().filter(i -> i.getId().equals(o.getValue())).findFirst().orElse(null);
                o.setValue(imgData != null ? "https:" + imgData.getLink().replace("fill_size", "700x817") : "");
            }));

            attributeDTOVarchar.forEach(v -> v.getOptions().sort(OptionProductVarchar::compareTo));

            attributeOptionDTO.setProductId(product.getId());
            attributeOptionDTO.setTotalQuantity(quantity);
            attributeOptionDTO.setOptionProductDTOList(optionProductDTOList);
            attributeOptionDTO.setListAttributeVarchar(attributeDTOVarchar);

            return attributeOptionDTO;
        } catch (
                Exception ex) {
            return null;
        }
    }

    private void createAttributeVarchar(OptionProductVarchar optionProductVarchar, List<AttributeDTO<OptionProductVarchar>> attributeDTOVarchar
            , List<String> listIdImage, OptionProductDTO optionProductDTO, boolean isImage, IImageDataService repository) {
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

        if (optionProductDTO != null ) {
            List<OptionProductVarcharDTO> list = optionProductDTO.getOptionProductVarcharList();

            if (attr.getCode().equals("image")) {
                List<OptionProductVarcharDTO> listImg = optionProductDTO.getListImages();
                OptionProductVarcharDTO opVarchar = new OptionProductVarcharDTOMapper().mapRow(optionProductVarchar);
                ImageData imgData = repository.findImageById(opVarchar.getValue());
                opVarchar.setValue(imgData != null ? "https:" + imgData.getLink().replace("fill_size", "700x817") : "");
                listImg.add(opVarchar);
                if (isImage) {
                    list.add(opVarchar);
                }
                optionProductDTO.setListImages(listImg);
            } else {
                list.add(new OptionProductVarcharDTOMapper().mapRow(optionProductVarchar));
            }
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
