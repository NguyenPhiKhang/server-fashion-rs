package com.khangse616.serverfashionrs.mappers.impl;

import com.khangse616.serverfashionrs.mappers.RowMapper;
import com.khangse616.serverfashionrs.models.Product;
import com.khangse616.serverfashionrs.models.dto.ProductDetailDTO;

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
            productDetailDTO.setBrand(product.getBrand() != null ? product.getBrand().getName() : null);
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

            listProductAttr.forEach(pd -> {

            });

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
