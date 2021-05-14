package com.khangse616.serverfashionrs.services;

import com.khangse616.serverfashionrs.models.SeenProduct;

import java.util.List;

public interface ISeenProductService {
    void CreateOrUpdateSeenProduct(int userId, int productId);
    List<SeenProduct> getListSeenProduct(int userId);
}
