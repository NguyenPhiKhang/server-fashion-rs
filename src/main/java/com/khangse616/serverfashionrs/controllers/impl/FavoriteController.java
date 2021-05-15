package com.khangse616.serverfashionrs.controllers.impl;

import com.khangse616.serverfashionrs.controllers.IFavoriteController;
import com.khangse616.serverfashionrs.mappers.impl.ProductItemDTOMapper;
import com.khangse616.serverfashionrs.models.dto.ProductItemDTO;
import com.khangse616.serverfashionrs.services.IFavoriteService;
import com.khangse616.serverfashionrs.services.IImageDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1")
public class FavoriteController implements IFavoriteController {
    @Autowired
    private IFavoriteService favoriteProductService;

    @Autowired
    private IImageDataService imageDataService;

    @Override
    public String createOrUpdateFavoriteOfUser(int userId, int productId) {
        favoriteProductService.updateFavoriteOfUser(userId, productId);
        return "Đã cập nhật";
    }

    @Override
    public List<ProductItemDTO> getListFavoriteProduct(int userId) {
        return favoriteProductService.getListFavoriteProduct(userId).stream().map(value-> new ProductItemDTOMapper().mapRow(value.getProduct(), imageDataService)).collect(Collectors.toList());
    }
}
