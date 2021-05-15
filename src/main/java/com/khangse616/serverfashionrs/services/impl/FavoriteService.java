package com.khangse616.serverfashionrs.services.impl;

import com.khangse616.serverfashionrs.models.Favorite;
import com.khangse616.serverfashionrs.models.ProductUserKey;
import com.khangse616.serverfashionrs.repositories.FavoriteRepository;
import com.khangse616.serverfashionrs.services.IFavoriteService;
import com.khangse616.serverfashionrs.services.IProductService;
import com.khangse616.serverfashionrs.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class FavoriteService implements IFavoriteService {
    @Autowired
    private FavoriteRepository favoriteRepository;

    @Autowired
    private IProductService productService;

    @Autowired
    private IUserService userService;

    @Override
    public void updateFavoriteOfUser(int userId, int productId) {
        if (favoriteRepository.existsFavoriteProductByIdProductIdAndIdUserId(productId, userId)) {
            Favorite favoriteProduct = favoriteRepository.findFavoriteProductByIdProductIdAndIdUserId(productId, userId);
            favoriteProduct.setLike(!favoriteProduct.isLike());
            favoriteProduct.setTimeUpdated(new Timestamp(System.currentTimeMillis()));

            favoriteRepository.save(favoriteProduct);
        } else {
            Favorite favoriteProduct = new Favorite();
            favoriteProduct.setLike(true);
            favoriteProduct.setTimeUpdated(new Timestamp(System.currentTimeMillis()));
            favoriteProduct.setProduct(productService.findProductByIdVisibleTrue(productId));
            favoriteProduct.setUser(userService.getUserById(userId));
            favoriteProduct.setId(new ProductUserKey(productId, userId));

            favoriteRepository.save(favoriteProduct);
        }
    }

    @Override
    public List<Favorite> getListFavoriteProduct(int userId) {
        return favoriteRepository.getFavoriteByIdUserIdOrderByTimeUpdatedDesc(userId);
    }
}
