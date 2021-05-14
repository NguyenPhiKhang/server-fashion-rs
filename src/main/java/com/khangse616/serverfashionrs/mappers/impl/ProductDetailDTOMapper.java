package com.khangse616.serverfashionrs.mappers.impl;

import com.khangse616.serverfashionrs.mappers.RowMapper;
import com.khangse616.serverfashionrs.models.*;
import com.khangse616.serverfashionrs.models.dto.*;
import com.khangse616.serverfashionrs.services.IImageDataService;

import java.util.*;
import java.util.stream.Collectors;

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
            productDetailDTO.setRatingStar(product.getRatingStar());

            AttributeOptionDTO attributeOptionDTO = new AttributeOptionDTOMapper().mapRow(product, imageDataService);

            productDetailDTO.setTotalQuantity(attributeOptionDTO.getTotalQuantity());
            productDetailDTO.setOptionProductDTOList(attributeOptionDTO.getOptionProductDTOList());
            productDetailDTO.setListAttributeVarchar(attributeOptionDTO.getListAttributeVarchar());

            productDetailDTO.setCategory(product.getCategory());

            Category category = product.getCategory();
            StringBuilder categoriesStr = new StringBuilder();

            for (int i = product.getCategory().getLevel(); i >= 0; i--) {
                categoriesStr.insert(0, "/" + category.getId());
                category = category.getParentCategory();
            }
            categoriesStr.insert(0, 0);

            productDetailDTO.setCategories(categoriesStr.toString());

            RatingProductDTO ratingProductDTO = new RatingProductDTO();
            Set<Rating> ratings = product.getRatings().stream().sorted(Comparator.comparing(Rating::getTimeUpdated)).limit(2).collect(Collectors.toSet());;

            List<RatingDTO> ratingDTOList = new ArrayList<>();

            for(Rating rating: ratings){
                RatingDTO ratingDTO = new RatingDTOMapper().mapRow(rating);
                ratingDTOList.add(ratingDTO);
            }

            ratingProductDTO.setData(ratingDTOList);
            productDetailDTO.setRatings(ratingProductDTO);

            return productDetailDTO;
        } catch (
                Exception ex) {
            return null;
        }
    }
}
