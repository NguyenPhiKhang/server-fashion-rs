package com.khangse616.serverfashionrs.services;

import com.khangse616.serverfashionrs.models.Favorite;
import com.khangse616.serverfashionrs.models.SeenProduct;

import java.util.List;

public interface IFavoriteService {
    void updateFavoriteOfUser(int userId, int productId);
    List<Favorite> getListFavoriteProduct(int userId);
    boolean checkUserLikedProduct(int userId, int productId);
}
