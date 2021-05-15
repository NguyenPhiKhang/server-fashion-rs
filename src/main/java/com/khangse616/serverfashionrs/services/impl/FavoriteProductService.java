package com.khangse616.serverfashionrs.services.impl;

import com.khangse616.serverfashionrs.models.FavoriteProduct;
import com.khangse616.serverfashionrs.models.ProductUserKey;
import com.khangse616.serverfashionrs.models.SeenProduct;
import com.khangse616.serverfashionrs.repositories.FavoriteProductRepository;
import com.khangse616.serverfashionrs.services.IFavoriteProductService;
import com.khangse616.serverfashionrs.services.IProductService;
import com.khangse616.serverfashionrs.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Service
public class FavoriteProductService implements IFavoriteProductService {
    @Autowired
    private FavoriteProductRepository favoriteProductRepository;

    @Autowired
    private IProductService productService;

    @Autowired
    private IUserService userService;

    @Override
    public void updateFavoriteProduct(int userId, int productId) {
        if (favoriteProductRepository.existsFavoriteProductByIdProductIdAndIdUserId(productId, userId)) {
            FavoriteProduct favoriteProduct = favoriteProductRepository.findFavoriteProductByIdProductIdAndIdUserId(productId, userId);
            favoriteProduct.setLike(!favoriteProduct.isLike());
            favoriteProduct.setTimeSeen(new Timestamp(System.currentTimeMillis()));

            favoriteProductRepository.save(favoriteProduct);
        } else {
            FavoriteProduct favoriteProduct = new FavoriteProduct();
            favoriteProduct.setLike(true);
            favoriteProduct.setTimeSeen(new Timestamp(System.currentTimeMillis()));
            favoriteProduct.setProduct(productService.findProductByIdVisibleTrue(productId));
            favoriteProduct.setUser(userService.getUserById(userId));
            favoriteProduct.setId(new ProductUserKey(productId, userId));

            favoriteProductRepository.save(favoriteProduct);
        }
    }
}
