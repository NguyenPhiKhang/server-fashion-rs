package com.khangse616.serverfashionrs.mappers.impl;

import com.khangse616.serverfashionrs.mappers.RowMapper;
import com.khangse616.serverfashionrs.models.*;
import com.khangse616.serverfashionrs.models.dto.AttributeDTO;
import com.khangse616.serverfashionrs.models.dto.ProductDetailDTO;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class ProductDetailDTOMapper implements RowMapper<ProductDetailDTO, Product> {
    @Override
    public ProductDetailDTO mapRow(Product product) {
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

            Set<Product> listProductAttr = product.getProductLinks();

            List<AttributeDTO<OptionProductVarchar>> attributeDTOVarchar = new ArrayList<>();
//            List<AttributeDTO<OptionProductDecimal>> attributeDTODecimal = new ArrayList<>();
//            List<AttributeDTO<OptionProductInteger>> attributeDTOInt = new ArrayList<>();

            product.getProductLinks().forEach(pd -> {
                pd.getOptionProductVarchars().forEach(optionProductVarchar -> {
                    Attribute attr = optionProductVarchar.getAttribute();
                    AttributeDTO<OptionProductVarchar> attributeDTOStream = attributeDTOVarchar.stream().filter(c -> c.getId() == attr.getId()).findFirst().orElse(null);
                    if (attributeDTOStream == null) {
                        AttributeDTO<OptionProductVarchar> attr1 = new AttributeDTO<OptionProductVarchar>(attr.getId(), attr.getType(), attr.getLabel(), attr.getCode());
                        List<OptionProductVarchar> optionProductVarchars = new ArrayList<>();
                        optionProductVarchars.add(optionProductVarchar);
                        attr1.setOptions(optionProductVarchars);

                        attributeDTOVarchar.add(attr1);
                    } else {
                        List<OptionProductVarchar> optionProductVarchars = attributeDTOStream.getOptions();
                        if(optionProductVarchars.stream().noneMatch(i->i.getId() == optionProductVarchar.getId())){
                            if(attr.getCode().equals("image"))

                            optionProductVarchars.add(optionProductVarchar);
                        }
                        attributeDTOStream.setOptions(optionProductVarchars);
                    }
                });
//
//                pd.getOptionProductDecimals().forEach(optionProductDecimal -> {
//                    Attribute attr = optionProductDecimal.getAttribute();
//                    AttributeDTO<OptionProductDecimal> attributeDTOStream = attributeDTODecimal.stream().filter(c -> c.getId() == attr.getId()).findFirst().orElse(null);
//                    if (attributeDTOStream == null) {
//                        AttributeDTO<OptionProductDecimal> attr1 = new AttributeDTO<OptionProductDecimal>(attr.getId(), attr.getType(), attr.getLabel(), attr.getCode());
//                        List<OptionProductDecimal> optionProductDecimals = new ArrayList<>();
//                        optionProductDecimals.add(optionProductDecimal);
//                        attr1.setOptions(optionProductDecimals);
//
//                        attributeDTODecimal.add(attr1);
//                    } else {
//                        List<OptionProductDecimal> optionProductDecimals = attributeDTOStream.getOptions();
//                        if (optionProductDecimals.stream().noneMatch(c -> c.getValue().equals(optionProductDecimal.getValue()))) {
//                            optionProductDecimals.add(optionProductDecimal);
//                        }
//                        attributeDTOStream.setOptions(optionProductDecimals);
//                    }
//                });
//
//                pd.getOptionProductIntegers().forEach(optionProductInteger -> {
//                    Attribute attr = optionProductInteger.getAttribute();
//                    AttributeDTO<OptionProductInteger> attributeDTOStream = attributeDTOInt.stream().filter(c -> c.getId() == attr.getId()).findFirst().orElse(null);
//                    if (attributeDTOStream == null) {
//                        AttributeDTO<OptionProductInteger> attr1 = new AttributeDTO<OptionProductInteger>(attr.getId(), attr.getType(), attr.getLabel(), attr.getCode());
//                        List<OptionProductInteger> optionProductIntegers = new ArrayList<>();
//                        optionProductIntegers.add(optionProductInteger);
//                        attr1.setOptions(optionProductIntegers);
//
//                        attributeDTOInt.add(attr1);
//                    } else {
//                        List<OptionProductInteger> optionProductIntegers = attributeDTOStream.getOptions();
//                        optionProductIntegers.add(optionProductInteger);
//                        attributeDTOStream.setOptions(optionProductIntegers);
//                    }
//                }
//                );
            });

            productDetailDTO.setListAttributeVarchar(attributeDTOVarchar);
//            productDetailDTO.setListAttributeInteger(attributeDTOInt);
//            productDetailDTO.setListAttributeDecimal(attributeDTODecimal);


//            productDetailDTO.set(product.getPrice());
//            productDetailDTO.setFinalPrice(product.getFinalPrice());
//            productDetailDTO.setDescription(product.getDescription());
//            productDetailDTO.setShortDescription(product.getShortDescription());
//            productDetailDTO.setWeight(product.getWeight());
//            productDetailDTO.setQuantity(product.getQuantity());
//            productDetailDTO.setActive(product.isActive());
//            productDetailDTO.setShopFreeShipping(product.isShopFreeShipping());
//            productDetailDTO.setOrderCount(product.getOrderCount());
//            productDetailDTO.setStockStatus(product.isStockStatus());
//            productDetailDTO.setSku(product.getSku());
//            productDetailDTO.setSkuUser(product.getSkuUser());
//            productDetailDTO.setRatingStar(product.getRatingStar());
////            productDetailDTO.setImages(product.getImages().stream().map(image -> ImageUtil.addressImage(image.getId()))
////                    .collect(Collectors.toList()));
//            productDetailDTO.setImages(product.getImages().stream().map(Image::getLink).collect(Collectors.toList()));
//            List<AttributeProductDTO> attributeProductDTOList = new ArrayList<>();
//
//            for (Option option: product.getOptions()){
//                if(attributeProductDTOList.stream().anyMatch(o -> o.getId() == option.getAttribute().getId())){
//                    AttributeProductDTO attributeProductDTO = attributeProductDTOList.stream().filter(o->o.getId()==option.getAttribute().getId()).findFirst().get();
//                    OptionProductDTO  optionProductDTO = new OptionProductDTOMapper().mapRow(option);
//                    attributeProductDTO.addValue(optionProductDTO);
//                }else{
//                    Attribute attribute = option.getAttribute();
//                    AttributeProductDTO attributeProductDTO = new AttributeProductDTOMapper().mapRow(attribute);
//                    OptionProductDTO  optionProductDTO = new OptionProductDTOMapper().mapRow(option);
//                    List<OptionProductDTO> list = new ArrayList<>();
//                    list.add(optionProductDTO);
//                    attributeProductDTO.setValue(list);
//                    attributeProductDTOList.add(attributeProductDTO);
//                }
//            }
//
//            productDetailDTO.setAttribute(attributeProductDTOList);
//
//            CommentProductDTO commentProductDTO = new CommentProductDTO();
//            commentProductDTO.setTotalCount(product.getComments().size());
//            List<CommentDTO> commentDTOList = new ArrayList<>();
//
//            for(Comment comment: product.getComments()){
//                CommentDTO commentDTO = new CommentDTOMapper().mapRow(comment);
//                commentDTOList.add(commentDTO);
//            }
//            commentProductDTO.setData(commentDTOList);
//
//            productDetailDTO.setComments(commentProductDTO);
//
//            RatingProductDTO ratingProductDTO = new RatingProductDTO();
//            ratingProductDTO.setTotalCount(product.getRatings().size());
//            List<RatingDTO> ratingDTOList = new ArrayList<>();
//
//            for(Rating rating: product.getRatings().stream().sorted(Comparator.comparing(Rating::getTimeUpdated)).limit(2).collect(Collectors.toList())){
//                RatingDTO ratingDTO = new RatingDTOMapper().mapRow(rating);
//                ratingDTOList.add(ratingDTO);
//            }
//            ratingProductDTO.setData(ratingDTOList);
//            productDetailDTO.setRatings(ratingProductDTO);
//
//            productDetailDTO.setCategories(new CategoriesDTOMapper().mapRow(List.copyOf(product.getCategories())));
//            productDetailDTO.setShop(new ShopDTOMapper().mapRow(product.getShop()));

            return productDetailDTO;
        } catch (Exception ex) {
            return null;
        }
    }


}
